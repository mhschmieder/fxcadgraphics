/*
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

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities to work with standard surface materials via enumerated values.
 */
public final class SurfaceMaterialUtilities {

    // NOTE: The constructor is disabled, as this is a static class.
    private SurfaceMaterialUtilities() {}
 
    // Get the list of available Surface Materials, presented alphabetically,
    // but currently using the common abbreviated names.
    public static List< String > getSurfaceMaterialNames() {
        final ArrayList< String > surfaceMaterials = new ArrayList<>();

        surfaceMaterials.add( SurfaceMaterial.ACOUSTIC_TILE_ON_RIGID_SURF_KF
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.BRICK_WALL_PAINTED_LB
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.BRICK_WALL_UNPAINTED_LB
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.CARPET_HEAVY_ON_CONCRETE_CH
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.CONCRETE_BLOCK_PAINTED_CH
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.CONCRETE_BLOCK_UNPAINTED_CH
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.PLASTER_ON_LATHE_CH
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.POURED_CONCRETE_PAINTED_LB
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.POURED_CONCRETE_UNPAINTED_LB
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.RIGID
                              .abbreviation() );
        surfaceMaterials.add( SurfaceMaterial.VELOUR_TEN_OZ_PER_YARD_SQR_TOUCHING_WALL_CH
                              .abbreviation() );

        return surfaceMaterials;
    }
}
