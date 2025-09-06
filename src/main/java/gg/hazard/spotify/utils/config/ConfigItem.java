package gg.hazard.spotify.utils.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigItem {
    private Material material;
    private int data;
    private int slot;

    private String name;
    private int amount;
    private List<String> lore;

    private boolean runCommand;
    private String command;

    private String head;
}
