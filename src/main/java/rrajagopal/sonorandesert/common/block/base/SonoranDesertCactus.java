package rrajagopal.sonorandesert.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SonoranDesertCactus extends CactusBlock {
    public SonoranDesertCactus(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        for (Direction direction: Direction.Plane.HORIZONTAL) {
            if (reader.getFluidState(pos.relative(direction)).is(FluidTags.LAVA)) {
                return false;
            }
        }
        BlockState belowState = reader.getBlockState(pos.below());
        return (belowState.is(Tags.Blocks.SAND) || belowState.is(Tags.Blocks.GRAVEL))
                && !reader.getBlockState(pos.above()).getMaterial().isLiquid();
    }
}
