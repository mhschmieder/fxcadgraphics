/**
 * MIT License
 *
 * Copyright (c) 2020, 2025 Mark Schmieder
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the FxCadGraphics Library
 *
 * You should have received a copy of the MIT License along with the
 * FxCadGraphics Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxcadgraphics
 */
package com.mhschmieder.fxcadgraphics;

/**
 * An enumeration of standard surface materials as specified in Harry Ferdinand, 
 * Olson's Acoustical Engineering textbook from 1957, designed to cover the basic 
 * range of acoustic absorption properties and other attributes, and including 
 * named materials that are composites of several layers.
 * <p>
 * Although frequently used in the context of physics for material properties, or
 * acoustics for absorption and other characteristics, surface materials are also
 * important to general CAD modeling, for visual representation and lighting.
 */
public enum SurfaceMaterial {
    ACOUSTIC_TILE_ON_RIGID_SURF_KF,
    BRICK_WALL_PAINTED_LB,
    BRICK_WALL_UNPAINTED_LB,
    CARPET_HEAVY_ON_CONCRETE_CH,
    CONCRETE_BLOCK_PAINTED_CH,
    CONCRETE_BLOCK_UNPAINTED_CH,
    PLASTER_ON_LATHE_CH,
    POURED_CONCRETE_PAINTED_LB,
    POURED_CONCRETE_UNPAINTED_LB,
    RIGID,
    VELOUR_TEN_OZ_PER_YARD_SQR_TOUCHING_WALL_CH;

    public static SurfaceMaterial defaultValue() {
        return RIGID;
    }
    
    public static String toLegacyString( final SurfaceMaterial surfaceMaterial ) {
        String legacyString = "";
        
        switch ( surfaceMaterial ) {
        case ACOUSTIC_TILE_ON_RIGID_SURF_KF:
            legacyString = "AcousticTileOnRigidSurfKF";
            break;
        case BRICK_WALL_PAINTED_LB:
            legacyString = "BrickWallPaintedLB";
            break;
        case BRICK_WALL_UNPAINTED_LB:
            legacyString = "BrickWallUnpaintedLB";
            break;
        case CARPET_HEAVY_ON_CONCRETE_CH:
            legacyString = "CarpetHeavyOnConcreteCH";
            break;
        case CONCRETE_BLOCK_PAINTED_CH:
            legacyString = "ConcreteBlockPaintedCH";
            break;
        case CONCRETE_BLOCK_UNPAINTED_CH:
            legacyString = "ConcreteBlockUnpaintedCH";
            break;
        case PLASTER_ON_LATHE_CH:
            legacyString = "PlasterOnLathCH";
            break;
        case POURED_CONCRETE_PAINTED_LB:
            legacyString = "PouredConcretePaintedLB";
            break;
        case POURED_CONCRETE_UNPAINTED_LB:
            legacyString = "PouredConcreteUnpaintedLB";
            break;
        case RIGID:
            legacyString = "Rigid";
            break;
        case VELOUR_TEN_OZ_PER_YARD_SQR_TOUCHING_WALL_CH:
            legacyString = "VelourTenOzPerYardSqrTouchingWallCH";
            break;
        default:
            final String errMessage = "Unexpected SurfaceMaterial " + surfaceMaterial;
            throw new IllegalArgumentException( errMessage );
        }
        
        return legacyString;
    }
    
    public static String toPresentrationString(final SurfaceMaterial surfaceMaterial ) {
        // TODO: Replace the legacy string forwarding with more descriptive labels.
        return toLegacyString( surfaceMaterial );
    }
    
    public String toLegacyString() {
        return toLegacyString( this );
    }
   
    public String toPresentationString() {
        return toPresentrationString( this );
    }
}
