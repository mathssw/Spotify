package com.maths.spotify.utils.config.creator;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.maths.spotify.utils.config.Config;
import com.maths.spotify.utils.config.creator.annotation.Header;
import com.maths.spotify.utils.config.creator.annotation.Path;

import java.lang.reflect.Field;

@Getter
public class ConfigCreator implements Listener {

	private final Config config;

	public ConfigCreator(JavaPlugin plugin, String file) {
		this.config = new Config(plugin, file);
		
		reload();
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDisable(PluginDisableEvent event) {
		save();
	}
	
	private void setup() {
		if (this.getClass().isAnnotationPresent(Header.class)) {
			StringBuilder header = new StringBuilder();
			for (String line : this.getClass().getAnnotation(Header.class).lines()) {
				header.append(line).append("\n");
			}
			
			config.options().header(header.toString());
		}

		for (Field field : this.getClass().getDeclaredFields()) {
			if (!field.isAnnotationPresent(Path.class)) {
				continue;
			}

			Path path = field.getAnnotation(Path.class);
			if (config.contains(path.id())) {
				try {

					field.setAccessible(true);

					Object update = deserialize(path, field.getType());
					field.set(null, update);

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				config.set(path.id(), serialize(path, field));
			}
		}
		
		config.save();
	}
	
	public void reload() {
		config.reload();
		setup();
	}
	
	public <T> void updateField(String path, Object value) {
		if (value.getClass().equals(ChatColor.class)) {
			config.set(path, ((ChatColor) value).name());
		} else {
			config.set(path, value);
		}

		config.save();
		
		setup();
	}

	private void save() {
		for (Field field : this.getClass().getDeclaredFields()) {
			if (!field.isAnnotationPresent(Path.class)) {
				continue;
			}

			Path path = field.getAnnotation(Path.class);
			config.set(path.id(), serialize(path, field));
		}

		config.save();
	}

	private <T> Object deserialize(Path path, Class<?> type) {
		if (type.equals(ChatColor.class)) {
			ChatColor color = ChatColor.WHITE;
			try {
				color = ChatColor.valueOf(config.getString(path.id()));
			} catch (Exception ignored) {}
			return color;
		} else if (type.equals(Material.class)) {
			Material material = Material.STONE;
			try {
				material = Material.getMaterial(config.getString(path.id()));
			} catch (Exception ignored) {}
			return material;
		}

		return config.get(path.id());
	}

	private Object serialize(Path path, Field field) {
		try {
			if (field.getType().equals(ChatColor.class)) {
				ChatColor color = (ChatColor) field.get(null);
				return color.name();
			} else if (field.getType().equals(Material.class)) {
				Material material = (Material) field.get(null);
				return material.name();
			}

			return field.get(null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
}