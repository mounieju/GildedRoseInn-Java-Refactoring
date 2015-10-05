package fr.iutvalence.info.dut.m3105.gildedroseinn.refactoring;

import java.util.ArrayList;
import java.util.List;

public class GildedRose
{

	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		System.out.println("OMGHAI!");

		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

		updateQualityAndSellInForAllItems();
	}

	public static void updateQualityAndSellInForAllItems()
	{
		for (Item item : items) 
		{
			updateQualityAndSellInForItem(item);
		}
	}

	
	private static void updateQualityAndSellInForItem(Item item) 
	{
		if (isLegendary(item))
		{
			return ;
		}		
		
		updateQuality(item);
		updateSellIn(item);

		if (item.getSellIn() < 0)
		{
			updateQualityOfExpiredItem(item);
		}
	}

	
	private static void updateQuality(Item item) 
	{
		if (isNormal(item))
		{
			decreaseQuality(item);
		}
		else
		{
			increaseQuality(item);

			if (isBackstagePass(item))
			{
				if (item.getSellIn() < 11)
				{
					increaseQuality(item);
				}
	
				if (item.getSellIn() < 6)
				{
					increaseQuality(item);
				}
			}
		}
	}

	
	private static void updateQualityOfExpiredItem(Item item) 
	{
		if (!"Aged Brie".equals(item.getName()))
		{
			if (!isBackstagePass(item))
			{
				decreaseQuality(item);
			}
			else
			{
				item.setQuality(0);
			}
		}
		else
		{
			increaseQuality(item);
		}
	}

	
	private static void updateSellIn(Item item) 
	{
			item.setSellIn(item.getSellIn() - 1);
	}

	
	private static void increaseQuality(Item item) 
	{
		if (item.getQuality() < 50)
		{
			item.setQuality(item.getQuality() + 1);
		}
	}
	

	private static void decreaseQuality(Item item) 
	{
		if (item.getQuality() > 0)
		{
				item.setQuality(item.getQuality() - 1);
		}
	}
	
	
	private static boolean isLegendary(Item item) 
	{
		return "Sulfuras, Hand of Ragnaros".equals(item.getName());
	}
	
	
	private static boolean isBackstagePass(Item item) 
	{
		return "Backstage passes to a TAFKAL80ETC concert".equals(item.getName());
	}

	
	private static boolean isNormal(Item item) 
	{
		return (!"Aged Brie".equals(item.getName()))
				&& !isBackstagePass(item);
	}
}