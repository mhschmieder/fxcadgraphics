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
 * This file is part of the FxCadToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * FxCadToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxcadtoolkit
 */
package com.mhschmieder.fxcadgraphics;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.mhschmieder.fxgraphicstoolkit.geometry.FacingDirection;
import com.mhschmieder.fxgraphicstoolkit.geometry.Orientation;
import com.mhschmieder.fxgraphicstoolkit.shape.ShapeUtilities;
import com.mhschmieder.fxlayergraphics.LayerUtilities;
import com.mhschmieder.fxlayergraphics.model.LayerProperties;
import com.mhschmieder.mathtoolkit.geometry.euclidian.OrthogonalAxes;
import com.mhschmieder.mathtoolkit.geometry.euclidian.VectorUtilities;
import com.mhschmieder.physicstoolkit.MassComputable;
import com.mhschmieder.physicstoolkit.MassProperties;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.shape.Shape;

/**
 * The <code>PhysicsObject</code> class is the abstract base class for all
 * physics objects. It describes the attributes that are common to all physics
 * objects in a 3D view (even if projected to 2D), such as Mass Properties.
 * <p>
 * NOTE: All data should be private, in case of overrides on getter methods.
 * Also, this means member variables should not be accessed directly, in case of
 * overrides on getter methods in subclasses.
 */
public abstract class PhysicsObject extends SolidObject implements MassComputable, CogMarkable {

    // Cache the current Mass Properties associated with this Physics Object.
    private MassProperties _massProperties;

    // NOTE: Since this class declares additional fields to the parent class,
    // we cannot just invoke the super-constructor from each constructor, but
    // need to invoke incrementally more complex local constructors instead.
    public PhysicsObject() {
        this( LayerUtilities.makeDefaultLayer(),
              GC_IN_VENUE_COORDINATES_DEFAULT,
              ANGLE_DEGREES_DEFAULT,
              ORIENTATION_DEFAULT,
              FACING_DIRECTION_DEFAULT,
              INVERTED_DEFAULT,
              new MassProperties() );
    }

    // Fully qualified constructor.
    protected PhysicsObject( final LayerProperties layer,
                             final Point3D gcInVenueCoordinates,
                             final double angleDegrees,
                             final Orientation orientation,
                             final FacingDirection facingDirection,
                             final boolean inverted,
                             final MassProperties massProperties ) {
        // Always call the super-constructor first!
        super( layer, gcInVenueCoordinates, angleDegrees, orientation, facingDirection, inverted );

        // Set the initial mass Properties reference for COG and Weight.
        _massProperties = massProperties;
    }

    @Override
    public boolean equals( final Object obj ) {
        if ( !( obj instanceof PhysicsObject ) ) {
            return false;
        }

        // NOTE: We invoke getter methods vs. directly accessing data
        //  members, so that derived classes produce the correct results when
        //  comparing two objects.
        final PhysicsObject other = ( PhysicsObject ) obj;
        if ( !super.equals( obj ) || !getMassProperties().equals( other.getMassProperties() ) ) {
            return false;
        }

        return true;
    }

    // NOTE: This is in non-JavaFX units as it inherits a Physics Library method.
    @Override
    public final Vector3D getCogInObjectCoordinates() {
        return _massProperties.getCogInObjectCoordinates();
    }

    // NOTE: This is in non-JavaFX units as it references a Physics Library method.
    public final Vector2D getCogInPlanarCoordinates() {
        final Vector3D cogInVenueCoordinates = getCogInVenueCoordinates();
        final Vector2D cogInPlanarCoordinates = VectorUtilities
                .projectToPlane( cogInVenueCoordinates, OrthogonalAxes.XY );
        return cogInPlanarCoordinates;
    }

    // NOTE: This is in non-JavaFX units as it references a Physics Library method.
    public final Vector3D getCogInVenueCoordinates() {
        final Vector3D cogInObjectCoordinates = getCogInObjectCoordinates();
        final Point3D fxCogInObjectCoordinates = new Point3D( cogInObjectCoordinates.getX(), 
                                                              cogInObjectCoordinates.getY(), 
                                                              cogInObjectCoordinates.getZ() );
        final Point3D cogInVenueCoordinates = getVectorInVenueCoordinatesFromObjectCoordinates( 
                fxCogInObjectCoordinates );
        return new Vector3D( cogInVenueCoordinates.getX(), 
                             cogInVenueCoordinates.getY(), 
                             cogInVenueCoordinates.getZ() );
    }

    @Override
    public final List< Shape > getCogMarkerGraphics() {
        // The default crosshairs diameter is assigned to be roughly half the
        // depth of an M1D, then 75% of that so that it's smaller than the COG.
        final double crosshairDimension = 0.15d;

        // We need to use a list of shapes with multiple visual elements, as we
        // have several categories of markers.
        final Vector2D cogLocation = getCogInPlanarCoordinates();
        final Point2D fxCogLocation = new Point2D( cogLocation.getX(), 
                                                   cogLocation.getY() );
        final List< Shape > cogMarkerGraphics = ShapeUtilities
                .getCrosshairGraphics( fxCogLocation, crosshairDimension );

        return cogMarkerGraphics;
    }

    public final MassProperties getMassProperties() {
        return _massProperties;
    }

    @Override
    public final double getWeightKg() {
        final double weightKg = _massProperties.getWeightKg();
        return weightKg;
    }

    @Override
    public int hashCode() {
        // TODO: Replace auto-generated method stub?
        return super.hashCode();
    }

    @Override
    public final boolean isCogValid() {
        final boolean cogValid = _massProperties.isCogValid();
        return cogValid;
    }

    public final void setMassProperties( final MassProperties massProperties ) {
        _massProperties = massProperties;
    }
}
