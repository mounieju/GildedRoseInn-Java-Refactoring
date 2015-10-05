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
		for (int currentItem = 0; currentItem < items.size(); currentItem++)
		{
			Item item = items.get(currentItem);
			updateQualityAndSellInForItem(item);
		}
	}

	
	private static void updateQualityAndSellInForItem(Item item) 
	{
		if (!isInalterable(item))
		{
			updateQuality(item);
			updateSellIn(item);

			if (item.getSellIn() < 0)
			{
				updateQualityOfExpiredItem(item);
			}
		}		
	}

	
	private static void updateQuality(Item item) 
	{
		if ((!"Aged Brie".equals(item.getName()))
				&& !"Backstage passes to a TAFKAL80ETC concert".equals(item.getName()))
		{
			if (item.getQuality() > 0)
			{
					item.setQuality(item.getQuality() - 1);
			}
		}
		else
		{
			if (item.getQuality() < 50)
			{
				item.setQuality(item.getQuality() + 1);

				if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName()))
				{
					if (item.getSellIn() < 11)
					{
						if (item.getQuality() < 50)
						{
							item.setQuality(item.getQuality() + 1);
						}
					}

					if (item.getSellIn() < 6)
					{
						if (item.getQuality() < 50)
						{
							item.setQuality(item.getQuality() + 1);
						}
					}
				}
			}
		}
	}

	

	private static void updateQualityOfExpiredItem(Item item) 
	{
		if (!"Aged Brie".equals(item.getName()))
		{
			if (!"Backstage passes to a TAFKAL80ETC concert".equals(item.getName()))
			{
				if (item.getQuality() > 0)
				{				
						item.setQuality(item.getQuality() - 1);
				}
			}
			else
			{
				item.setQuality(item.getQuality() - item.getQuality());
			}
		}
		else
		{
			if (item.getQuality() < 50)
			{
				item.setQuality(item.getQuality() + 1);
			}
		}
	}

	
	private static void updateSellIn(Item item) 
	{
			item.setSellIn(item.getSellIn() - 1);
	}

	
	private static boolean isInalterable(Item item) 
	{
		return "Sulfuras, Hand of Ragnaros".equals(item.getName());
	}
}