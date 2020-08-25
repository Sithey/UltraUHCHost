package fr.sithey.ultrauhchost.lib;


import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldGenerator;

import java.util.Random;

public class SugarGenerator extends WorldGenerator {
    @Override
    public boolean generate(World world, Random random, BlockPosition blockposition) {

        int iu = 100;
        for (int i = 0; i < iu; ++i) {
            BlockPosition blockposition1 = blockposition.a(random.nextInt(16) - random.nextInt(16), random.nextInt(16) - random.nextInt(16), random.nextInt(16) - random.nextInt(16));
            if((blockposition1.getY() > 58) && (blockposition1.getY() < 75)) {
                if (world.isEmpty(blockposition1)) {
                    BlockPosition blockposition2 = blockposition1.down();

                    if (world.getType(blockposition2.west()).getBlock().getMaterial().equals(net.minecraft.server.v1_8_R3.Material.WATER) ||  world.getType(blockposition2.east()).getBlock().getMaterial().equals(net.minecraft.server.v1_8_R3.Material.WATER) ||  world.getType(blockposition2.north()).getBlock().getMaterial().equals(net.minecraft.server.v1_8_R3.Material.WATER) || world.getType(blockposition2.south()).getBlock().getMaterial().equals(net.minecraft.server.v1_8_R3.Material.WATER)) {
                        int j = 2 + random.nextInt(random.nextInt(3) + 1);

                        for (int k = 0; k < j; ++k) {
                            if (Blocks.REEDS.e(world, blockposition1)) {
                                world.setTypeAndData(blockposition1.up(k), Blocks.REEDS.getBlockData(), 2);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
