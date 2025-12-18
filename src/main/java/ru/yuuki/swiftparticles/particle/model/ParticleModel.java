package ru.yuuki.swiftparticles.particle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Particle;
import org.bukkit.World;
import ru.yuuki.swiftparticles.particle.scheduler.ParticleScheduler;
import ru.yuuki.swiftparticles.vector.model.Vector3d;

@Getter @Setter
public final class ParticleModel {
    private final String name;
    private final Vector3d position, offsets;
    private final World world;
    private final Particle particle;
    private final ParticleScheduler scheduler;
    private final int amount;

    public ParticleModel(String name, Vector3d position, Vector3d offsets, World world, Particle particle, long delay, int amount) {
        this.name = name;
        this.position = position;
        this.offsets = offsets;
        this.world = world;
        this.particle = particle;
        this.amount = amount;
        this.scheduler = new ParticleScheduler(delay, this);
    }

    /*
     * Закругляемся
     */
    public void stop() {
        if (this.scheduler != null && !this.scheduler.isCancelled()) this.scheduler.cancel();
    }
}
