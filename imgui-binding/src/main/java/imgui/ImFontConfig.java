package imgui;

public final class ImFontConfig implements ImDestroyable {
    final long ptr;

    private short[] glyphRanges;

    /**
     * This class will create a native structure.
     * Call {@link #destroy()} method to manually free used memory.
     */
    public ImFontConfig() {
        ImGui.touch();
        ptr = nCreate();
        setFontDataOwnedByAtlas(false); // Read method javadoc
    }

    ImFontConfig(final long ptr) {
        this.ptr = ptr;
        setFontDataOwnedByAtlas(false); // Read method javadoc
    }

    @Override
    public void destroy() {
        nDestroy(ptr);
    }

    /*JNI
        #include <stdint.h>
        #include <imgui.h>
        #include "jni_common.h"

        jfieldID imFontConfigPtrID;

        #define IM_FONT_CONFIG ((ImFontConfig*)env->GetLongField(object, imFontConfigPtrID))
     */

    static native void nInit(); /*
        jclass jImFontConfigClass = env->FindClass("imgui/ImFontConfig");
        imFontConfigPtrID = env->GetFieldID(jImFontConfigClass, "ptr", "J");
    */

    private native long nCreate(); /*
        ImFontConfig* imFontConfig = new ImFontConfig();
        return (intptr_t)imFontConfig;
    */

    private native void nDestroy(long ptr); /*
        delete (ImFontConfig*)ptr;
    */

    /**
     * TTF/OTF data
     */
    public native byte[] getFontData(); /*
        int size = IM_FONT_CONFIG->FontDataSize;
        jbyteArray jBytes = env->NewByteArray(size);
        env->SetByteArrayRegion(jBytes, 0, size, (jbyte*)IM_FONT_CONFIG->FontData);
        return jBytes;
    */

    /**
     * TTF/OTF data
     */
    public native void setFontData(byte[] fontData); /*
        IM_FONT_CONFIG->FontData = &fontData[0];
    */

    /**
     * TTF/OTF data size
     */
    public native int getFontDataSize(); /*
        return IM_FONT_CONFIG->FontDataSize;
    */

    /**
     * TTF/OTF data size
     */
    public native void setFontDataSize(int fontDataSize); /*
        IM_FONT_CONFIG->FontDataSize = fontDataSize;
    */

    /**
     * TTF/OTF data ownership taken by the container ImFontAtlas (will delete memory itself).
     */
    public native boolean getFontDataOwnedByAtlas(); /*
        return IM_FONT_CONFIG->FontDataOwnedByAtlas;
    */

    /**
     * TTF/OTF data ownership taken by the container ImFontAtlas (will delete memory itself).
     * <p>
     * BINDING NOTICE: By default binding will set this value to <b>false</b>.
     * If this is not done, Dear ImGui will try to free memory allocated by JVM to store fonts data while running {@link ImGui#destroyContext()}.
     * This will result into a native exception, since JVM by itself controls it's own resources.
     */
    public native void setFontDataOwnedByAtlas(boolean isFontDataOwnedByAtlas); /*
        IM_FONT_CONFIG->FontDataOwnedByAtlas = isFontDataOwnedByAtlas;
    */

    /**
     * Index of font within TTF/OTF file
     */
    public native int getFontNo(); /*
        return IM_FONT_CONFIG->FontNo;
    */

    /**
     * Index of font within TTF/OTF file
     */
    public native void setFontNo(int fontNo); /*
        IM_FONT_CONFIG->FontNo = fontNo;
    */

    /**
     * Size in pixels for rasterizer (more or less maps to the resulting font height).
     */
    public native float getSizePixels(); /*
        return IM_FONT_CONFIG->SizePixels;
    */

    /**
     * Size in pixels for rasterizer (more or less maps to the resulting font height).
     */
    public native void setSizePixels(float sizePixels); /*
        IM_FONT_CONFIG->SizePixels = sizePixels;
    */

    /**
     * Rasterize at higher quality for sub-pixel positioning.
     * Read https://github.com/nothings/stb/blob/master/tests/oversample/README.md for details.
     */
    public native int getOversampleH(); /*
        return IM_FONT_CONFIG->OversampleH;
    */

    /**
     * Rasterize at higher quality for sub-pixel positioning.
     * Read https://github.com/nothings/stb/blob/master/tests/oversample/README.md for details.
     */
    public native void setOversampleH(int oversampleH); /*
        IM_FONT_CONFIG->OversampleH = oversampleH;
    */

    /**
     * Rasterize at higher quality for sub-pixel positioning.
     * We don't use sub-pixel positions on the Y axis.
     */
    public native int getOversampleV(); /*
        return IM_FONT_CONFIG->OversampleV;
    */

    /**
     * Rasterize at higher quality for sub-pixel positioning.
     * We don't use sub-pixel positions on the Y axis.
     */
    public native void setOversampleV(int oversampleV); /*
        IM_FONT_CONFIG->OversampleV = oversampleV;
    */

    /**
     * Align every glyph to pixel boundary. Useful e.g. if you are merging a non-pixel aligned font with the default font.
     * If enabled, you can set OversampleH/V to 1.
     */
    public native boolean getPixelSnapH(); /*
        return IM_FONT_CONFIG->PixelSnapH;
    */

    /**
     * Align every glyph to pixel boundary. Useful e.g. if you are merging a non-pixel aligned font with the default font.
     * If enabled, you can set OversampleH/V to 1.
     */
    public native void setPixelSnapH(boolean isPixelSnapH); /*
        IM_FONT_CONFIG->PixelSnapH = isPixelSnapH;
    */

    /**
     * Extra spacing (in pixels) between glyphs. Only X axis is supported for now.
     */
    public native void getGlyphExtraSpacing(ImVec2 dstImVec2); /*
        Jni::ImVec2Cpy(env, &IM_FONT_CONFIG->GlyphExtraSpacing, dstImVec2);
    */

    /**
     * Extra spacing (in pixels) between glyphs. Only X axis is supported for now.
     */
    public native void setGlyphExtraSpacing(float x, float y); /*
        IM_FONT_CONFIG->GlyphExtraSpacing.x = x;
        IM_FONT_CONFIG->GlyphExtraSpacing.y = y;
    */

    /**
     * Offset all glyphs from this font input.
     */
    public native void getGlyphOffset(ImVec2 dstImVec2); /*
        Jni::ImVec2Cpy(env, &IM_FONT_CONFIG->GlyphOffset, dstImVec2);
    */

    /**
     * Offset all glyphs from this font input.
     */
    public native void setGlyphOffset(float x, float y); /*
        IM_FONT_CONFIG->GlyphOffset.x = x;
        IM_FONT_CONFIG->GlyphOffset.y = y;
    */

    /**
     * Pointer to a user-provided list of Unicode range (2 value per range, values are inclusive, zero-terminated list).
     * THE ARRAY DATA NEEDS TO PERSIST AS LONG AS THE FONT IS ALIVE.
     */
    public short[] getGlyphRanges() {
        return glyphRanges;
    }

    /**
     * Pointer to a user-provided list of Unicode range (2 value per range, values are inclusive, zero-terminated list).
     * THE ARRAY DATA NEEDS TO PERSIST AS LONG AS THE FONT IS ALIVE.
     */
    public void setGlyphRanges(final short[] glyphRanges) {
        this.glyphRanges = glyphRanges;
        nSetGlyphRanges(glyphRanges);
    }

    private native void nSetGlyphRanges(short[] glyphRanges); /*
        IM_FONT_CONFIG->GlyphRanges = glyphRanges != NULL ? (ImWchar*)&glyphRanges[0] : NULL;
    */

    /**
     * Minimum AdvanceX for glyphs, set Min to align font icons, set both Min/Max to enforce mono-space font
     */
    public native float getGlyphMinAdvanceX(); /*
        return IM_FONT_CONFIG->GlyphMinAdvanceX;
    */

    /**
     * Minimum AdvanceX for glyphs, set Min to align font icons, set both Min/Max to enforce mono-space font
     */
    public native void setGlyphMinAdvanceX(float glyphMinAdvanceX); /*
        IM_FONT_CONFIG->GlyphMinAdvanceX = glyphMinAdvanceX;
    */

    /**
     * Maximum AdvanceX for glyphs
     */
    public native float getGlyphMaxAdvanceX(); /*
        return IM_FONT_CONFIG->GlyphMaxAdvanceX;
    */

    /**
     * Maximum AdvanceX for glyphs
     */
    public native void setGlyphMaxAdvanceX(float glyphMaxAdvanceX); /*
        IM_FONT_CONFIG->GlyphMaxAdvanceX = glyphMaxAdvanceX;
    */

    /**
     * Merge into previous ImFont, so you can combine multiple inputs font into one ImFont (e.g. ASCII font + icons + Japanese glyphs).
     * You may want to use GlyphOffset.y when merge font of different heights.
     */
    public native boolean getMergeMode(); /*
        return IM_FONT_CONFIG->MergeMode;
    */

    /**
     * Merge into previous ImFont, so you can combine multiple inputs font into one ImFont (e.g. ASCII font + icons + Japanese glyphs).
     * You may want to use GlyphOffset.y when merge font of different heights.
     */
    public native void setMergeMode(boolean mergeMode); /*
        IM_FONT_CONFIG->MergeMode = mergeMode;
    */

    /**
     * Settings for custom font rasterizer (e.g. ImGuiFreeType). Leave as zero if you aren't using one.
     */
    public native int getRasterizerFlags(); /*
        return IM_FONT_CONFIG->RasterizerFlags;
    */

    /**
     * Settings for custom font rasterizer (e.g. ImGuiFreeType). Leave as zero if you aren't using one.
     */
    public native void setRasterizerFlags(int rasterizerFlags); /*
        IM_FONT_CONFIG->RasterizerFlags = (unsigned int)rasterizerFlags;
    */

    /**
     * Brighten ({@code >}1.0f) or darken ({@code <}1.0f) font output. Brightening small fonts may be a good workaround to make them more readable.
     */
    public native float getRasterizerMultiply(); /*
        return IM_FONT_CONFIG->RasterizerMultiply;
    */

    /**
     * Brighten ({@code >}1.0f) or darken ({@code <}1.0f) font output. Brightening small fonts may be a good workaround to make them more readable.
     */
    public native void setRasterizerMultiply(float rasterizerMultiply); /*
        IM_FONT_CONFIG->RasterizerMultiply = rasterizerMultiply;
    */

    /**
     * Explicitly specify unicode codepoint of ellipsis character. When fonts are being merged first specified ellipsis will be used.
     */
    public native short getEllipsisChar(); /*
        return (short)IM_FONT_CONFIG->EllipsisChar;
    */

    /**
     * Explicitly specify unicode codepoint of ellipsis character. When fonts are being merged first specified ellipsis will be used.
     */
    public native void setEllipsisChar(short ellipsisChar); /*
        IM_FONT_CONFIG->EllipsisChar = (ImWchar)ellipsisChar;
    */

    // [Internal]

    /**
     * Name (strictly to ease debugging)
     */
    public native void setName(String name); /*
        strcpy(IM_FONT_CONFIG->Name, name);
    */
}
