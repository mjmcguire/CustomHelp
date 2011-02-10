package com.bukkit.Ndoto.CustomHelp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Handle events for all Player related events
 * @author Ndoto
 */
public class CustomHelpPlayerListener extends PlayerListener
{
    private final CustomHelp plugin;
    ArrayList<String> lines = new ArrayList<String>();
    ArrayList<String> data = new ArrayList<String>();
    //ArrayList<String> fixedLines = new ArrayList<String>();

    public CustomHelpPlayerListener(CustomHelp instance)
    {
        plugin = instance;
    }

    //Insert Player related code here
//    private ArrayList<String> readLines(String filename) throws IOException
//    {
//    	data.clear();
//    	FileReader fileReader = new FileReader(filename);
//    	BufferedReader bufferedReader = new BufferedReader(fileReader);
//    	String line = null;
//    	while ((line = bufferedReader.readLine()) != null)
//    	{
//    		data.add(line.toLowerCase());
//    	}
//    	bufferedReader.close();
//    	return data;
//    }
    
    private void linesProcess(Player player, int page){
    	int size = lines.size();
    	int pages;
    	
    	if (size % 9 == 0)
    	{
    		pages = size / 9;
    	}
    	else
    	{
    		pages = (size / 9) + 1;
    	}
    	
    	String header = ("§cHelp (Page " + Integer.toString(page) + " of " + Integer.toString(pages) + " §7| " + "[required] (optional)" + " §7| " + "§4/help <page #>");
    	int max = (page * 9);
    	int min = (page - 1) * 9;
    	player.sendMessage(header);
    	for (int i = min; ((i < max) && (i < size)); i++)
    	{
    		player.sendMessage(lines.get(i));
    	}
    }
    
    public void onPlayerCommand(PlayerChatEvent event)
    {
    	String[] split = event.getMessage().split(" ");
    	Player player = event.getPlayer();
    	File folder = plugin.getDataFolder();
    	String folderName = folder.getParent();


    	lines.clear();
    	//fixedLines.clear();
    	if (split[0].equalsIgnoreCase("/help") || split[0].equalsIgnoreCase("/?"))
    	{
        	try
        	{
        		FileInputStream input = new FileInputStream(folderName + "/CustomHelp/help.txt");
        		Scanner scanner = new Scanner(input, "UTF-8");
        		while (scanner.hasNextLine())
        		{
        			try
        			{
        				lines.add(scanner.nextLine());
        			}
        			catch (Exception exception)
        			{
        				lines.add(" ");
        			}
        		}
        		scanner.close();
        		input.close();
        	
        		int page;
        		try
        		{
        			page = Integer.parseInt(split[1]);
        		}
        		catch (Exception exception)
        		{
        			page = 1;
        		}
        		linesProcess(player, page);
        	}
        	catch (Exception exception)
        	{
        		player.sendMessage("CustomHelp error: could not open help.txt");
        	}
        	event.setCancelled(true);
    	}
    }
}

