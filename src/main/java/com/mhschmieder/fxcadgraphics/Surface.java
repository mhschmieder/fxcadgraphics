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

import com.mhschmieder.fxlayergraphics.LayerUtilities;

import javafx.scene.shape.Line;

/**
 * The <code>Surface</code> class is the implementation class for a Surface as
 * used in some CAD apps. This class describes Surface Materials, ID, enabled
 * status and Surface End Points.
 * <p>
 * NOTE: This class is kept around in case of future use, but is effectively
 *  replaced by the newer {@code SurfaceProperties} observable class.
 */
public final class Surface extends CartesianLine {

    // Surfaces are bypassed by default as they are only approximate.
    public static final boolean BYPASSED_DEFAULT       = true;

    // Declare an identifier number for this surface
    private static final int    SURFACE_NUMBER_DEFAULT = 1;

    // Declare the surface material name.
    public static final String  MATERIAL_NAME_DEFAULT  = SurfaceMaterial.RIGID
            .toPresentationString();

    private boolean             _bypassed              = BYPASSED_DEFAULT;
    private int                 _surfaceNumber         = SURFACE_NUMBER_DEFAULT;
    private String              _materialName          = MATERIAL_NAME_DEFAULT;

    // This is the default constructor; it sets all instance variables to
    // default values.
    // NOTE: As this class declares additional fields to the parent class, we
    // cannot just invoke the super-constructor from each constructor, so we
    // need to invoke incrementally more complex local constructors instead.
    public Surface() {
        this( SURFACE_NUMBER_DEFAULT );
    }

    // This is the preferred default constructor using a unique Surface ID.
    public Surface( final int surfaceNumber ) {
        this( surfaceNumber, BYPASSED_DEFAULT, MATERIAL_NAME_DEFAULT );
    }

    // This is the partially qualified constructor, when all but extents are
    // known.
    public Surface( final int surfaceNumber, final boolean bypassed, final String materialName ) {
        this( surfaceNumber,
              bypassed,
              materialName,
              CartesianLine.X1_DEFAULT,
              CartesianLine.Y1_DEFAULT,
              CartesianLine.X2_DEFAULT,
              CartesianLine.Y2_DEFAULT );
    }

    // This is the fully qualified constructor, using separate coordinates.
    // TODO: Pass in and use a unique Surface Name and Layer.
    @SuppressWarnings("nls")
    public Surface( final int surfaceNumber,
                    final boolean bypassed,
                    final String materialName,
                    final double x1,
                    final double y1,
                    final double x2,
                    final double y2 ) {
        super( x1, y1, x2, y2, "", LayerUtilities.makeDefaultLayer(), false, 1 );

        setSurfaceNumber( surfaceNumber );
        setBypassed( bypassed );
        setMaterialName( materialName );
    }

    // This is the fully qualified constructor, using a Line.
    // TODO: Pass in and use a unique Surface Name and Layer.
    @SuppressWarnings("nls")
    public Surface( final int surfaceNumber,
                    final boolean bypassed,
                    final String materialName,
                    final Line line ) {
        super( line, "", LayerUtilities.makeDefaultLayer(), false, 1 );

        setSurfaceNumber( surfaceNumber );
        setBypassed( bypassed );
        setMaterialName( materialName );
    }

    // NOTE: This is the copy constructor, and is offered in place of clone()
    // to guarantee that the source object is never modified by the new target
    // object created here.
    public Surface( final Surface surface ) {
        super();

        setSurface( surface );
    }

    // NOTE: Cloning is disabled as it is dangerous; use the copy constructor
    // instead.
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override
    public boolean equals( final Object obj ) {
        if ( !( obj instanceof Surface ) ) {
            return false;
        }

        // NOTE: We invoke getter methods vs. directly accessing data
        //  members, so that derived classes produce the correct results when
        //  comparing two objects.
        final Surface other = ( Surface ) obj;
        if ( !super.equals( obj ) 
                || ( getSurfaceNumber() != other.getSurfaceNumber() ) 
                || ( isBypassed() != other.isBypassed() ) 
                || !getMaterialName().equals( other.getMaterialName() ) ) {
            return false;
        }

        // NOTE: The "label" and "layer" properties are exempt, as they are not
        //  implemented at this level. The Layer is handled nonetheless by the
        //  super-parent but will match when defaulted so is not an issue.
        return true;
    }

    public String getMaterialName() {
        return _materialName;
    }

    public int getSurfaceNumber() {
        return _surfaceNumber;
    }

    @Override
    public int hashCode() {
        // TODO: Replace auto-generated method stub?
        return super.hashCode();
    }

    public boolean isBypassed() {
        return _bypassed;
    }

    public void setBypassed( final boolean bypassed ) {
        _bypassed = bypassed;
    }

    public void setMaterialName( final String materialName ) {
        _materialName = materialName;
    }

    // Fully qualified pseudo-constructor
    // TODO: Pass in and use a unique surface name and layer.
    @SuppressWarnings("nls")
    protected void setSurface( final Line line,
                               final int surfaceNumber,
                               final boolean bypassed,
                               final String materialName ) {
        setCartesianLine( line, "", LayerUtilities.makeDefaultLayer(), false, 1 );
        setSurfaceNumber( surfaceNumber );
        setBypassed( bypassed );
        setMaterialName( materialName );
    }

    // Pseudo-copy constructor.
    // TODO: Pass in and use a unique Surface Name.
    protected void setSurface( final Surface surface ) {
        setSurface( surface.getLine(),
                    surface.getSurfaceNumber(),
                    surface.isBypassed(),
                    surface.getMaterialName() );
    }

    public void setSurfaceNumber( final int surfaceNumber ) {
        _surfaceNumber = surfaceNumber;
    }
}
