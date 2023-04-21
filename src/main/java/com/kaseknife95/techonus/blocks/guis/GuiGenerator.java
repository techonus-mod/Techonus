package com.kaseknife95.techonus.blocks.guis;

import com.kaseknife95.techonus.blocks.containers.ContainerGenerator;
import com.kaseknife95.techonus.blocks.tileentities.TileEntityGenerator;
import com.kaseknife95.techonus.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGenerator extends GuiContainer
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/burning_generator.png");
    private final InventoryPlayer player;
    private final TileEntityGenerator tileentity;

    public GuiGenerator(InventoryPlayer player, TileEntityGenerator tileentity)
    {
        super(new ContainerGenerator(player, tileentity));
        this.player = player;
        this.tileentity = tileentity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String tileName = this.tileentity.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString(this.tileentity.getEnergyStored() + "TF", 115, 72, 4210752);


    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);

        int l = this.getEnergyStoredScaled(75);
        this.drawTexturedModalRect(this.guiLeft + 152, this.guiTop + 7, 176, 32, 16, 76 - l);
    }

    private int getEnergyStoredScaled(int pixels)
    {
        int i = this.tileentity.getEnergyStored();
        int j = this.tileentity.getMaxEnergyStored();
        return i != 0 && j != 0 ? i * pixels / j : 0;
    }


    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileentity.getField(2);

        if (i == 0)
        {
            i = 200;
        }

        return this.tileentity.getField(1) * pixels / i;
    }
}
