package ru.yuuki.swiftparticles;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;
import ru.yuuki.swiftparticles.particle.manager.ParticlesManager;

@Getter @Setter
public final class SwiftParticles extends JavaPlugin {
    @Getter
    private static SwiftParticles instance;
    private ParticlesManager particlesManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        this.particlesManager = new ParticlesManager();
    }

    @Override
    public void onDisable() {
        
    }

    public static String getMessage(String path) {
        return SwiftParticles.getInstance().getConfig().getString("messages.prefix").replace("&", "§") + SwiftParticles.getInstance().getConfig().getString(path).replace("&", "§");
    }
}
