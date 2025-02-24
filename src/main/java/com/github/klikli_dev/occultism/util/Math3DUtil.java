/*
 * MIT License
 *
 * Copyright 2020 klikli-dev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.klikli_dev.occultism.util;

import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Math3DUtil {

    //region Static Methods
    public static double yaw(Vector3d a, Vector3d b) {
        double dirx = a.x - b.x;
        double diry = a.y - b.y;
        double dirz = a.z - b.z;

        double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);

        dirx /= len;
        diry /= len;
        dirz /= len;

        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);

        //calculate just for reference
        pitch = pitch * 180.0 / Math.PI;
        yaw = yaw * 180.0 / Math.PI;

        yaw += 90f;

        return yaw;
    }

    public static Vector3d center(BlockPos pos) {
        return new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
    }

    public static BlockPos clamp(BlockPos pos, AxisAlignedBB bounds) {
        return new BlockPos(
                MathHelper.clamp(pos.getX(), bounds.minX, bounds.maxX),
                MathHelper.clamp(pos.getY(), bounds.minY, bounds.maxY),
                MathHelper.clamp(pos.getZ(), bounds.minZ, bounds.maxZ)
        );
    }

    public static AxisAlignedBB bounds(ChunkPos pos, int maxHeight) {
        return new AxisAlignedBB(pos.getMinBlockX(), 0, pos.getMinBlockZ(), pos.getMaxBlockX(), maxHeight, pos.getMaxBlockZ());
    }

    /**
     * Gets all blocks in the given direction and distance, sorted by distance from closest to furthest.
     * Does not stop on hit!
     *
     * @param start     the start position
     * @param direction the trace direction
     * @param distance  the trace distance
     * @return a list of block positions
     */
    public static List<BlockPos> simpleTrace(BlockPos start, Direction direction, int distance) {
        //map to a new block pos because getAllInBox uses a mutable blockpos internally for iteration,
        // leading to the same block being collected 6x when not mapping it to an immutable blockpos
        return BlockPos.betweenClosedStream(start, start.relative(direction, distance)).map(BlockPos::immutable)
                .sorted(Comparator.comparingDouble(start::distSqr)).collect(Collectors.toList());
    }

    /**
     * Gets the first blockPos fulfilling the hit condition in the given direction and distance.
     *
     * @param start        the start position
     * @param direction    the trace direction
     * @param distance     the trace distance
     * @param hitCondition the hit condition.
     * @return the hit block pos, or null if no hit.
     */
    public static BlockPos simpleTrace(BlockPos start, Direction direction, int distance,
                                       Predicate<BlockPos> hitCondition) {
        List<BlockPos> positions = simpleTrace(start, direction, distance);

        for (BlockPos pos : positions)
            if (hitCondition.test(pos))
                return pos;
        return null;
    }
    //endregion Static Methods
}
