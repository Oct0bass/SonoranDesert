package rrajagopal.sonorandesert.common.block;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
//import net.minecraft.state.StateContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import rrajagopal.sonorandesert.common.block.base.SonoranDesertCactus;

public class BlockBarrelCactus extends SonoranDesertCactus {
    private final BooleanProperty CUT = BooleanProperty.create("cut");

    public BlockBarrelCactus(Block.Properties properties) {
        super(properties);
        //this.setDefaultState(this.getStateContainer().getBaseState().with(CUT, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(2, 0, 2, 14, 9, 14);
    }

//    @Override
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
//        super.fillStateContainer(builder);
//        builder.add(CUT);
//    }
}
