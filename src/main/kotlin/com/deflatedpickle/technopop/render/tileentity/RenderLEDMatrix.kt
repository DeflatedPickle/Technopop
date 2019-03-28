package com.deflatedpickle.technopop.render.tileentity

import com.deflatedpickle.technopop.tileentity.TileEntityLEDMatrix
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

class RenderLEDMatrix : TileEntitySpecialRenderer<TileEntityLEDMatrix>() {
    private val textureLight = ResourceLocation("technopop:textures/blocks/led_matrix_overlay_2x2.png")

    private val tessellator = Tessellator.getInstance()

    override fun render(te: TileEntityLEDMatrix, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha)

        // One
        drawLight(x, y, z, -9.0 / 16.0, 1.0 / 16.0, te.one)
        // println("One: ${te.one}")
        // Two
        drawLight(x, y, z, -1.0 / 16.0, 1.0 / 16.0, te.two)
        // println("Two: ${te.two}")
        // Three
        drawLight(x, y, z, -9.0 / 16.0, 9.0 / 16.0, te.three)
        // println("Three: ${te.three}")
        // Four
        drawLight(x, y, z, -1.0 / 16.0, 9.0 / 16.0, te.four)
        // println("Four: ${te.four}")
    }

    private fun drawLight(x: Double, y: Double, z: Double, xOffset: Double, zOffset: Double, isOn: Boolean) {
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glDisable(GL11.GL_LIGHTING)

        GlStateManager.translate(x, y, z)
        GlStateManager.translate(xOffset, 0.0, zOffset)
        Minecraft.getMinecraft().textureManager.bindTexture(textureLight)

        val buffer = tessellator.buffer
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)

        if (isOn) {
            GL11.glColor3f(1f, 0.3f, 0.35f)
        }

        // Bottom Left
        buffer.pos(1.0, 4.0 / 16.0, 0.0).tex(0.0, 0.0).endVertex()
        // Bottom Right
        buffer.pos(0.0, 4.0 / 16.0, 0.0).tex(0.0, 1.0).endVertex()
        // Top Right
        buffer.pos(0.0, 4.0 / 16.0, 1.0).tex(1.0, 1.0).endVertex()
        // Top Left
        buffer.pos(1.0, 4.0 / 16.0, 1.0).tex(1.0, 0.0).endVertex()

        tessellator.draw()

        GL11.glColor3f(1f, 1f, 1f)
        GL11.glEnable(GL11.GL_LIGHTING)
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }
}