package ru.yuuki.swiftparticles.vector.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public final class Vector3d {
    private double x, y, z;

    public static Vector3d parseFrom(List<Integer> list) {
        if (list.size() == 3) {
            return new Vector3d(list.get(0), list.get(1), list.get(2));
        } else {
            throw new IllegalArgumentException("Размер списка не равен 3");
        }
    }

    public static Location asLocation(final World world, final Vector3d vector) {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ());
    }
}
