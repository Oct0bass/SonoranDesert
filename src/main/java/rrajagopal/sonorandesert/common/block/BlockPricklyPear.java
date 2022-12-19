package rrajagopal.sonorandesert.common.block;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import rrajagopal.sonorandesert.common.block.base.SonoranDesertCactus;

public class BlockPricklyPear extends SonoranDesertCactus {
    public BlockPricklyPear(Block.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(2, 0, 2, 14, 12, 14);
    }
}
