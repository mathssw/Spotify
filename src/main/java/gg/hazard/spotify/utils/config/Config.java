package gg.hazard.spotify.utils.config;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

@Getter
public class Config extends YamlConfiguration {

    private final String file;
    private final JavaPlugin plugin;

    public Config(JavaPlugin plugin, String file) {
        this.plugin = plugin;
        this.file = file;

        this.createFile();
        this.load();
    }

    private void createFile() {
        File file = new File(plugin.getDataFolder(), this.file);

        // Create any directories leading to this file if we have too
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            if (!file.exists()) {
                if (plugin.getResource(this.file) != null) {
                    plugin.saveResource(this.file, false);
                } else {
                    file.createNewFile();
                }
            }

            super.load(file);
        } catch (Exception ex) {
            plugin.getLogger().log(Level.SEVERE, "Config \"" + this.file + "\" could not be loaded!");
            ex.printStackTrace();
        }
    }

    public void deleteFile() {
        File file = new File(plugin.getDataFolder(), this.file);
        file.delete();
    }

    public void load() {
        try {
            File file = new File(plugin.getDataFolder(), this.file);

            if (!file.exists()) {
                this.createFile();
            } else {
            	super.load(file);
            }
        } catch (Exception ex) {
            plugin.getLogger().log(Level.SEVERE, "Config \"" + this.file + "\" could not be loaded!");
            ex.printStackTrace();
        }
    }


    public void save() {
        File file = new File(plugin.getDataFolder(), this.file);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            super.save(file);
        } catch (Exception ex) {
            plugin.getLogger().log(Level.SEVERE, "Config \"" + this.file + "\" could not be saved!");
            ex.printStackTrace();
        }
    }
    
    public void reload() {
    	this.load();
    	this.save();
    }
}