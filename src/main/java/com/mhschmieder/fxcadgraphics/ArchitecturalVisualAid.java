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
package com.mhschmieder.fxcadgraphics;

import org.apache.commons.math3.util.FastMath;

import com.mhschmieder.fxlayergraphics.model.LayerProperties;
import com.mhschmieder.fxlayergraphics.LayerUtilities;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

/**
 * The <code>ArchitecturalVisualAid</code> class is the concrete class for
 * architectural visual aids as used in some CAD apps. It is really nothing
 * more than a line that is selectable.
 */
public class ArchitecturalVisualAid extends VisualAid {

    // Declare the default Architectural Visual Aid label.
    public static final String    ARCHITECTURAL_VISUAL_AID_LABEL_DEFAULT =
                                                                         "Architectural Visual Aid"; //$NON-NLS-1$

    // Declare default constants, where appropriate, for all fields.
    protected static final double X1_DEFAULT                             = 0.0d;
    protected static final double Y1_DEFAULT                             = 0.0d;
    protected static final double X2_DEFAULT                             = 1.0d;
    protected static final double Y2_DEFAULT                             = 1.0d;

    public static final String getArchitecturalVisualAidLabelDefault() {
        return ARCHITECTURAL_VISUAL_AID_LABEL_DEFAULT;
    }

    public static ArchitecturalVisualAid getDefaultArchitecturalVisualAid() {
        return new ArchitecturalVisualAid();
    }

    // Declare variables for Cartesian Space coordinates.
    private Line _line = new Line();

    // NOTE: Since this class declares additional fields to the parent class,
    // we cannot just invoke the super-constructor from each constructor, but
    // need to invoke incrementally more complex local constructors instead.
    public ArchitecturalVisualAid() {
        this( X1_DEFAULT,
              Y1_DEFAULT,
              X2_DEFAULT,
              Y2_DEFAULT,
              ARCHITECTURAL_VISUAL_AID_LABEL_DEFAULT,
              LayerUtilities.makeDefaultLayer(),
              USE_AS_LISTENER_PLANE_DEFAULT,
              NUMBER_OF_TARGET_ZONES_DEFAULT );
    }

    public ArchitecturalVisualAid( final ArchitecturalVisualAid architecturalVisualAid ) {
        super();

        setArchitecturalVisualAid( architecturalVisualAid );
    }

    public ArchitecturalVisualAid( final double x1,
                                   final double y1,
                                   final double x2,
                                   final double y2,
                                   final String architecturalVisualAidLabel,
                                   final LayerProperties layer,
                                   final boolean useAsListenerPlane,
                                   final int numberOfTargetZones ) {
        super();

        setArchitecturalVisualAid( x1,
                                   y1,
                                   x2,
                                   y2,
                                   architecturalVisualAidLabel,
                                   layer,
                                   useAsListenerPlane,
                                   numberOfTargetZones );
    }

    public ArchitecturalVisualAid( final Line line,
                                   final String architecturalVisualAidLabel,
                                   final LayerProperties layer ) {
        this( line,
              architecturalVisualAidLabel,
              layer,
              USE_AS_LISTENER_PLANE_DEFAULT,
              NUMBER_OF_TARGET_ZONES_DEFAULT );
    }

    public ArchitecturalVisualAid( final Line line,
                                   final String architecturalVisualAidLabel,
                                   final LayerProperties layer,
                                   final boolean useAsListenerPlane,
                                   final int numberOfTargetZones ) {
        this( line.getStartX(),
              line.getStartY(),
              line.getEndX(),
              line.getEndY(),
              architecturalVisualAidLabel,
              layer,
              useAsListenerPlane,
              numberOfTargetZones );
    }

    public ArchitecturalVisualAid( final Point2D p1,
                                   final Point2D p2,
                                   final String architecturalVisualAidLabel,
                                   final LayerProperties layer ) {
        this( p1,
              p2,
              architecturalVisualAidLabel,
              layer,
              USE_AS_LISTENER_PLANE_DEFAULT,
              NUMBER_OF_TARGET_ZONES_DEFAULT );
    }

    public ArchitecturalVisualAid( final Point2D p1,
                                   final Point2D p2,
                                   final String architecturalVisualAidLabel,
                                   final LayerProperties layer,
                                   final boolean useAsListenerPlane,
                                   final int numberOfTargetZones ) {
        super();

        setArchitecturalVisualAid( p1,
                                   p2,
                                   architecturalVisualAidLabel,
                                   layer,
                                   useAsListenerPlane,
                                   numberOfTargetZones );
    }

    @Override
    public final void drag( final double deltaX, final double deltaY ) {
        // Compute the new Line End Points for the Architectural Visual Aid by
        // combining the deltas with the original Line End Points.
        // TODO: Embed this logic in an overridden setLocation() method?
        final Line line = getLine();
        final double x1 = line.getStartX();
        final double y1 = line.getStartY();
        final double x2 = line.getEndX();
        final double y2 = line.getEndY();
        setLine( x1 + deltaX, y1 + deltaY, x2 + deltaX, y2 + deltaY );

        // Drag the associated Scene Graph Nodes to the same End Points.
        dragNode( deltaX, deltaY );
    }

    @Override
    public boolean equals( final Object obj ) {
        if ( !( obj instanceof ArchitecturalVisualAid ) ) {
            return false;
        }

        // NOTE: We invoke getter methods vs. directly accessing data
        // members, so that derived classes produce the correct results when
        // comparing two objects.
        final ArchitecturalVisualAid other = ( ArchitecturalVisualAid ) obj;
        if ( !super.equals( obj ) ) {
            return false;
        }

        return getLine().equals( other.getLine() );
    }

    @Override
    public final double getAngleDegrees() {
        final double xdiff = _line.getEndX() - _line.getStartX();
        final double ydiff = _line.getEndY() - _line.getStartY();

        // Convert Cartesian coordinates to Polar coordinates.
        // NOTE: The JavaFX Point2D class offers the angle(otherPoint)
        // method, but it uses acos() instead of atan() and thus isn't likely
        // as safe or as fast as our home-grown solution (atan() is native).
        final double angle = FastMath.atan2( ydiff, xdiff );

        return FastMath.toDegrees( angle );
    }

    @Override
    public final GraphicalObject getDeepClonedObject() {
        final ArchitecturalVisualAid architecturalVisualAidClone =
                                                                 new ArchitecturalVisualAid( this );

        return architecturalVisualAidClone;
    }

    public final double getDistance() {
        // Convert Cartesian coordinates to Polar coordinates (sqrt).
        final Point2D startPoint = getP1();
        final Point2D endPoint = getP2();
        final double distance = startPoint.distance( endPoint );

        return distance;
    }

    @Override
    public final Line getLine() {
        return _line;
    }

    @Override
    public int hashCode() {
        // TODO: Replace auto-generated method stub?
        return super.hashCode();
    }

    public final void setArchitecturalVisualAid( final ArchitecturalVisualAid architecturalVisualAid ) {
        setArchitecturalVisualAid( architecturalVisualAid.getLine(),
                                   architecturalVisualAid.getLabel(),
                                   architecturalVisualAid.getLayer(),
                                   architecturalVisualAid.isUseAsListenerPlane(),
                                   architecturalVisualAid.getNumberOfTargetZones() );
    }

    public final void setArchitecturalVisualAid( final double x1,
                                                 final double y1,
                                                 final double x2,
                                                 final double y2,
                                                 final String architecturalVisualAidLabel,
                                                 final LayerProperties layer,
                                                 final boolean useAsListenerPlane,
                                                 final int numberOfTargetZones ) {
        setLine( x1, y1, x2, y2 );
        setVisualAid( architecturalVisualAidLabel, layer, useAsListenerPlane, numberOfTargetZones );
    }

    public final void setArchitecturalVisualAid( final Line line,
                                                 final String architecturalVisualAidLabel,
                                                 final LayerProperties layer,
                                                 final boolean useAsListenerPlane,
                                                 final int numberOfTargetZones ) {
        setLine( line );
        setVisualAid( architecturalVisualAidLabel, layer, useAsListenerPlane, numberOfTargetZones );
    }

    public final void setArchitecturalVisualAid( final Point2D p1,
                                                 final double angleDegrees,
                                                 final double distance,
                                                 final String architecturalVisualAidLabel,
                                                 final LayerProperties layer,
                                                 final boolean useAsListenerPlane,
                                                 final int numberOfTargetZones ) {
        setLine( p1, angleDegrees, distance );
        setVisualAid( architecturalVisualAidLabel, layer, useAsListenerPlane, numberOfTargetZones );
    }

    public final void setArchitecturalVisualAid( final Point2D p1,
                                                 final Point2D p2,
                                                 final String architecturalVisualAidLabel,
                                                 final LayerProperties layer,
                                                 final boolean useAsListenerPlane,
                                                 final int numberOfTargetZones ) {
        setLine( p1, p2 );
        setVisualAid( architecturalVisualAidLabel, layer, useAsListenerPlane, numberOfTargetZones );
    }

    @Override
    public final void setLine( final double x1,
                               final double y1,
                               final double x2,
                               final double y2 ) {
        _line = new Line( x1, y1, x2, y2 );

        setLocationX( x1 );
        setLocationY( y1 );
    }

    public final void setLine( final Line line ) {
        setLine( line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY() );
    }

    public final void setLine( final Point2D p1,
                               final double angleDegrees,
                               final double distance ) {
        // Convert Polar coordinates to Cartesian coordinates.
        final double angleRadians = FastMath.toRadians( angleDegrees );
        final double x1 = p1.getX();
        final double y1 = p1.getY();
        final double x2 = x1 + ( distance * FastMath.cos( angleRadians ) );
        final double y2 = y1 + ( distance * FastMath.sin( angleRadians ) );

        setLine( x1, y1, x2, y2 );
    }

    public final void setLine( final Point2D p1, final Point2D p2 ) {
        setLine( p1.getX(), p1.getY(), p2.getX(), p2.getY() );
    }

    public final void setP1( final Point2D p1 ) {
        _line.setStartX( p1.getX() );
        _line.setStartY( p1.getY() );
    }

    public final void setP2( final Point2D p2 ) {
        _line.setEndX( p2.getX() );
        _line.setEndY( p2.getY() );
    }

    @Override
    public void setReferencePoint2D( final double referencePointX, final double referencePointY ) {
        final double deltaX = referencePointX - getX1();
        final double deltaY = referencePointY - getY1();
        drag( deltaX, deltaY );
    }

    @Override
    public void setReferencePoint2D( final Point2D referencePoint ) {
        final double referencePointX = referencePoint.getX();
        final double referencePointY = referencePoint.getY();
        setReferencePoint2D( referencePointX, referencePointY );
    }

    public final void setX1( final double x1 ) {
        _line.setStartX( x1 );
    }

    public final void setX2( final double x2 ) {
        _line.setEndX( x2 );
    }

    public final void setY1( final double y1 ) {
        _line.setStartY( y1 );
    }

    public final void setY2( final double y2 ) {
        _line.setEndY( y2 );
    }
}
