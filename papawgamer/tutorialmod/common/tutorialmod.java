package papawgamer.tutorialmod.common;

import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.SidedProxy;
import papawgamer.tutorialmod.common.TutorialModCommonProxy;
import papawgamer.tutorialmod.common.blocks.BlockLimestone;
import papawgamer.tutorialmod.common.handlers.TutorialModServerPacketHandler;
import papawgamer.tutorialmod.common.handlers.TutorialModClientPacketHandler;


@NetworkMod(clientSideRequired=true,serverSideRequired=true, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"tutorialmod"}, packetHandler = TutorialModClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"tutorialmod"}, packetHandler = TutorialModServerPacketHandler.class)) //For serverside packet handling

//MOD BASICS
@Mod(modid="tutorialmod",name="Tutorial Mod",version="Release")

public class tutorialmod {

@Instance("tutorialmod") //The instance, this is very important later on
public static tutorialmod instance = new tutorialmod();

@SidedProxy(clientSide = "papawgamer.tutorialmod.client.TutorialModClientProxy", serverSide = "papawgamer.tutorialmod.common.TutorialModCommonProxy") //Tells Forge the location of your proxies
public static TutorialModCommonProxy proxy;

//BLOCKS
public static Block Limestone;

@PreInit
public void PreInit(FMLPreInitializationEvent e){

//BLOCKS
	Limestone = new BlockLimestone(3000).setUnlocalizedName("Limestone"); //3000 is its ID
	
}

@Init
public void InitTutorialMod(FMLInitializationEvent event){ //Your main initialization method

//BLOCKS (METHOD)
proxy.registerBlocks(); //Calls the registerBlocks method

//MULTIPLAYER ABILITY
NetworkRegistry.instance().registerGuiHandler(this, proxy); //Registers the class that deals with GUI data

}
}