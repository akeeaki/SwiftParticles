package ru.yuuki.swiftparticles.particle.manager;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import ru.yuuki.swiftparticles.SwiftParticles;
import ru.yuuki.swiftparticles.particle.model.ParticleModel;
import ru.yuuki.swiftparticles.particle.scheduler.ParticleScheduler;
import ru.yuuki.swiftparticles.vector.model.Vector3d;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public final class ParticlesManager {
    private final Set<ParticleModel> particles = new HashSet<>();

    public ParticlesManager() {
        reload();
    }

    public void reload() {
        this.particles.forEach(ParticleModel::stop);
        this.particles.clear();

        final ConfigurationSection section = SwiftParticles.getInstance().getConfig().getConfigurationSection("particles");
        if (section == null || section.getKeys(false).isEmpty()) return;

        section.getKeys(false).forEach(key -> {
            final ConfigurationSection particleSection = section.getConfigurationSection(key);
            if (particleSection == null || particleSection.getKeys(false).isEmpty()) return;

            particleSection.getKeys(false).forEach(val -> {
                final World world = Bukkit.getWorld(particleSection.getString("world"));

                if (world == null) {
                    SwiftParticles.getInstance().getSLF4JLogger().error("World {} not found!", particleSection.getString("world"));
                    return;
                }

                Vector3d location = Vector3d.parseFrom(particleSection.getIntegerList("position"));
                Vector3d offsets = Vector3d.parseFrom(particleSection.getIntegerList("offsets"));
                Particle particle = Particle.valueOf(particleSection.getString("particle"));
                long delay = particleSection.getLong("delay");
                int amount = particleSection.getInt("amount");
                if (amount == 0) return;

                this.particles.add(new ParticleModel(key, location, offsets, world, particle, delay, amount));
                SwiftParticles.getInstance().getSLF4JLogger().info("Particle {} loaded!", key);
            });
        });
    }
}
