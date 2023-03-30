/**
 * MIT License
 *
 * Copyright (c) 2020, 2023 Mark Schmieder
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
 * This file is part of the FxCadToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * FxCadToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxcadtoolkit
 */
package com.mhschmieder.fxcadtoolkit;

import com.mhschmieder.physicstoolkit.SurfaceMaterialNames;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class SurfaceProperties {

    // Surfaces are bypassed by default as they are only approximate.
    public static final boolean   BYPASSED_DEFAULT      = true;

    // Declare the Surface Material default name.
    public static final String    MATERIAL_NAME_DEFAULT = SurfaceMaterialNames.RIGID;

    private final IntegerProperty surfaceNumber;
    private final StringProperty  surfaceName;
    private final BooleanProperty surfaceBypassed;
    private final StringProperty  materialName;

    public SurfaceProperties( final int pSurfaceNumber,
                              final String pSurfaceName,
                              final boolean pSurfaceBypassed,
                              final String pMaterialName ) {
        surfaceNumber = new SimpleIntegerProperty( pSurfaceNumber );
        surfaceName = new SimpleStringProperty( pSurfaceName );
        surfaceBypassed = new SimpleBooleanProperty( pSurfaceBypassed );
        materialName = new SimpleStringProperty( pMaterialName );
    }

    public String getMaterialName() {
        return materialName.get();
    }

    public String getSurfaceName() {
        return surfaceName.get();
    }

    public int getSurfaceNumber() {
        return surfaceNumber.get();
    }

    public boolean isSurfaceBypassed() {
        return surfaceBypassed.get();
    }

    public StringProperty materialNameProperty() {
        return materialName;
    }

    public void setMaterialName( final String pMaterialName ) {
        materialName.set( pMaterialName );
    }

    public void setSurfaceBypassed( final boolean pSurfaceBypassed ) {
        surfaceBypassed.set( pSurfaceBypassed );
    }

    public void setSurfaceName( final String pSurfaceName ) {
        surfaceName.set( pSurfaceName );
    }

    public void setSurfaceNumber( final int pSurfaceNumber ) {
        surfaceNumber.set( pSurfaceNumber );
    }

    public BooleanProperty surfaceBypassedProperty() {
        return surfaceBypassed;
    }

    public StringProperty surfaceNameProperty() {
        return surfaceName;
    }

    public IntegerProperty surfaceNumberProperty() {
        return surfaceNumber;
    }
}
