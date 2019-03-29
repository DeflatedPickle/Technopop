package com.deflatedpickle.technopop.render.tileentity

import com.deflatedpickle.technopop.tileentity.TileEntityPCBConnectionPoint
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.Vec3d
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA
import org.lwjgl.opengl.GL11.GL_SRC_ALPHA
import java.awt.Color

class RenderPCBConnectionPoint : TileEntitySpecialRenderer<TileEntityPCBConnectionPoint>() {
    private val textureWire = ResourceLocation("technopop:textures/blocks/pcb_transmission_line.png")

    private val tessellator = Tessellator.getInstance()

    private val mc = Minecraft.getMinecraft()

    override fun render(te: TileEntityPCBConnectionPoint, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha)

        GlStateManager.pushMatrix()
        Minecraft.getMinecraft().textureManager.bindTexture(textureWire)
        GL11.glDisable(GL11.GL_LIGHTING)

        for (i in te.connections) {
            val sX = i.x - te.pos.x + x + 0.5
            val sY = i.y - te.pos.y + y
            val sZ = i.z - te.pos.z + z + 0.5

            val hash = te.pos.x * te.pos.y * te.pos.z
            val colour = Color.decode(hash.toString())
            GL11.glColor3f(colour.red.toFloat() / 225, colour.green.toFloat() / 225, colour.blue.toFloat() / 225)

            this.renderBeam(sX, sY, sZ, x + 0.5, y, z + 0.5, mc.player.ticksExisted, 1.0, false)
        }

        GL11.glEnable(GL11.GL_LIGHTING)
        GlStateManager.popMatrix()
    }

    private fun renderBeam(x1: Double, y1: Double, z1: Double, x2: Double, y2: Double, z2: Double, ticks: Int, partialTicks: Double, renderBubbles: Boolean) {
        val bufferbuilder = tessellator.buffer

        GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT)
        GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT)

        GlStateManager.disableLighting()
        GlStateManager.disableCull()
        GlStateManager.disableBlend()

        GlStateManager.pushMatrix()
        GlStateManager.translate(x2.toFloat(), y2.toFloat(), z2.toFloat())

        val vec3d = Vec3d(x1, y1, z1)
        val vec3d1 = Vec3d(x2, y2, z2)

        var beamDirection = vec3d.subtract(vec3d1)
        val beamLength = beamDirection.length()
        beamDirection = beamDirection.normalize()

        val f5 = Math.acos(beamDirection.y).toFloat()
        val f6 = Math.atan2(beamDirection.z, beamDirection.x).toFloat()

        GlStateManager.rotate((Math.PI.toFloat() / 2f + -f6) * (180f / Math.PI.toFloat()), 0.0f, 1.0f, 0.0f)
        GlStateManager.rotate(f5 * (180f / Math.PI.toFloat()), 1.0f, 0.0f, 0.0f)

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX)

        val width = 0.05

        val lineX1 = 0.0 + width
        val lineZ1 = 0.0 + width
        val lineX2 = 0.0 - width
        val lineZ2 = 0.0 - width

        // UVs
        val d22 = 0.0
        val maxWidth = 2 / 16.0
        val maxHeight = 7 / 16.0

        // Top Left
        bufferbuilder.pos(lineX1, beamLength, lineZ1).tex(maxWidth, maxHeight).endVertex()
        // Bottom Left
        bufferbuilder.pos(lineX1, 0.0, lineZ1).tex(maxWidth, 0.0).endVertex()
        // Bottom Right
        bufferbuilder.pos(lineX2, 0.0, lineZ2).tex(0.0, 0.0).endVertex()
        // Top Right
        bufferbuilder.pos(lineX2, beamLength, lineZ2).tex(0.0, maxHeight).endVertex()
        tessellator.draw()

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX)
        GlStateManager.rotate(f5 * (180f / Math.PI.toFloat()), 0.0f, 1.0f, 0.0f)

        // Top Left
        bufferbuilder.pos(lineX1, beamLength, lineZ1).tex(maxWidth, maxHeight).endVertex()
        // Bottom Left
        bufferbuilder.pos(lineX1, 0.0, lineZ1).tex(maxWidth, 0.0).endVertex()
        // Bottom Right
        bufferbuilder.pos(lineX2, 0.0, lineZ2).tex(0.0, 0.0).endVertex()
        // Top Right
        bufferbuilder.pos(lineX2, beamLength, lineZ2).tex(0.0, maxHeight).endVertex()
        tessellator.draw()
        GlStateManager.popMatrix()
    }

    override fun isGlobalRenderer(te: TileEntityPCBConnectionPoint): Boolean {
        return true
    }
}