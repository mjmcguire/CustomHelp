package com.bukkit.Ndoto.CustomHelp;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * CustomHelp block listener
 * @author Ndoto
 */
public class CustomHelpBlockListener extends BlockListener
{
    private final CustomHelp plugin;

    public CustomHelpBlockListener(final CustomHelp plugin)
    {
        this.plugin = plugin;
    }

    //put all Block related code here
}
