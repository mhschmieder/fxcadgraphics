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

import com.mhschmieder.commonstoolkit.lang.NumberUtilities;
import com.mhschmieder.fxgraphicstoolkit.geometry.Extents2D;
import com.mhschmieder.physicstoolkit.DistanceUnit;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

/**
 * The <code>DrawingLimits</code> class is the implementation class for the
 * inclusive bounds of a CAD drawing, such as venues used in CAD apps. It
 * currently contains a rectangle describing the boundary of the CAD space, as
 * well as a flag for whether to auto-sync to another boundary (usually a
 * Region2D, such as one that is used as a Prediction Plane in CAD apps).
 *
 * This class is generally for 2D CAD, but is deliberately flexible towards
 * referring either to screen space (which is always 2D currently), or to
 * whatever projective two dimensional axes are currently in use.
 */
public final class DrawingLimits extends Extents2D {

    // Declare default constants, where appropriate, for all fields.
    public static final boolean     AUTO_SYNC_DEFAULT    = true;

    /**
     * Declare an invalid Bounding Box for convenience. According the the JavaFX
     * API docs, invalid Bounding Boxes are flagged by setting their width and
     * height to "-1". Default constructors might do that, but this is clearer.
     */
    public static final BoundingBox INVALID_BOUNDING_BOX = new BoundingBox( 0.0d, 0.0d, -1d, -1d );

    /** Cached observable copy of most recent auto-sync setting. */
    private final BooleanProperty   autoSync;

    // NOTE: This field has to follow JavaFX Property Beans conventions.
    public BooleanBinding           drawingLimitsChanged;

    /** Default constructor when nothing is known. */
    public DrawingLimits() {
        this( AUTO_SYNC_DEFAULT );
    }

    /*
     * Default constructor when only auto-sync is known. 
     */
    public DrawingLimits( final boolean pAutoSync ) {
        this( pAutoSync,
              X_METERS_DEFAULT,
              Y_METERS_DEFAULT,
              WIDTH_METERS_DEFAULT,
              HEIGHT_METERS_DEFAULT );
    }

    /*
     * Fully qualified constructor when all dimensions and auto-sync are known.
     */
    public DrawingLimits( final boolean pAutoSync,
                          final double pBoundaryX,
                          final double pBoundaryY,
                          final double pBoundaryWidth,
                          final double pBoundaryHeight ) {
        super( pBoundaryX, pBoundaryY, pBoundaryWidth, pBoundaryHeight );

        autoSync = new SimpleBooleanProperty( pAutoSync );

        // Bind all of the properties to the associated dirty flag.
        // NOTE: This is done during initialization, as it is best to make
        // singleton objects and just update their values vs. reconstructing.
        bindProperties();
    }

    /*
     * Fully qualified constructor when all dimensions and auto-sync are known.
     */
    public DrawingLimits( final boolean pAutoSync, final Extents2D pExtents ) {
        // Always call the super-constructor first!
        super( pExtents );

        autoSync = new SimpleBooleanProperty( pAutoSync );

        // Bind all of the properties to the associated dirty flag.
        // NOTE: This is done during initialization, as it is best to make
        // singleton objects and just update their values vs. reconstructing.
        bindProperties();
    }

    /*
     * Fully qualified constructor when all dimensions and auto-sync are known.
     */
    public DrawingLimits( final boolean pAutoSync, final Rectangle pBoundary ) {
        // Always call the super-constructor first!
        super( pBoundary );

        autoSync = new SimpleBooleanProperty( pAutoSync );

        // Bind all of the properties to the associated dirty flag.
        // NOTE: This is done during initialization, as it is best to make
        // singleton objects and just update their values vs. reconstructing.
        bindProperties();
    }

    /*
     * Fully qualified constructor when all dimensions and auto-sync are known.
     */
    public DrawingLimits( final boolean pAutoSync, final Rectangle2D pBounds ) {
        // Always call the super-constructor first!
        super( pBounds );

        autoSync = new SimpleBooleanProperty( pAutoSync );

        // Bind all of the properties to the associated dirty flag.
        // NOTE: This is done during initialization, as it is best to make
        // singleton objects and just update their values vs. reconstructing.
        bindProperties();
    }

    /*
     * Fully qualified constructor when all dimensions are known. 
     */
    public DrawingLimits( final Bounds computedBounds ) {
        this( computedBounds.getMinX(),
              computedBounds.getMinY(),
              computedBounds.getWidth(),
              computedBounds.getHeight() );
    }

    /*
     * Fully qualified constructor when all dimensions are known. 
     */
    public DrawingLimits( final double pBoundaryX,
                          final double pBoundaryY,
                          final double pBoundaryWidth,
                          final double pBoundaryHeight ) {
        this( AUTO_SYNC_DEFAULT, pBoundaryX, pBoundaryY, pBoundaryWidth, pBoundaryHeight );
    }

    /*
     * Copy constructor. 
     */
    public DrawingLimits( final DrawingLimits pDrawingLimits ) {
        this( pDrawingLimits.isAutoSync(),
              pDrawingLimits.getX(),
              pDrawingLimits.getY(),
              pDrawingLimits.getWidth(),
              pDrawingLimits.getHeight() );
    }

    /*
     * Fully qualified constructor when all dimensions are known. 
     */
    public DrawingLimits( final Extents2D pExtents ) {
        this( AUTO_SYNC_DEFAULT, pExtents );
    }

    /*
     * Fully qualified constructor when all dimensions are known. 
     */
    public DrawingLimits( final Rectangle2D pBounds ) {
        this( AUTO_SYNC_DEFAULT, pBounds );
    }

    public BooleanProperty autoSyncProperty() {
        return autoSync;
    }

    public void bindProperties() {
        // Establish the dirty flag criteria as any assignable value change.
        drawingLimitsChanged = new BooleanBinding() {
            {
                // When any of these assignable values change, the
                // drawingLimitsChanged Boolean Binding is invalidated and
                // notifies its listeners.
                super.bind( autoSyncProperty(),
                            xProperty(),
                            yProperty(),
                            widthProperty(),
                            heightProperty() );
            }

            // Just auto-clear the invalidation by overriding with a status that
            // is affirmative of a change having triggered the call.
            @Override
            protected boolean computeValue() {
                return true;
            }
        };
    }

    // NOTE: Cloning is disabled as it is dangerous; use the copy constructor
    // instead.
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public boolean isAutoSync() {
        return autoSync.get();
    }

    /** Default pseudo-constructor. */
    public void reset() {
        setDrawingLimits( AUTO_SYNC_DEFAULT,
                          X_METERS_DEFAULT,
                          Y_METERS_DEFAULT,
                          WIDTH_METERS_DEFAULT,
                          HEIGHT_METERS_DEFAULT );
    }

    public void setAutoSync( final boolean pAutoSync ) {
        autoSync.set( pAutoSync );
    }

    /*
     * Fully qualified pseudo-constructor. 
     */
    public void setDrawingLimits( final boolean pAutoSync,
                                  final double pBoundaryX,
                                  final double pBoundaryY,
                                  final double pBoundaryWidth,
                                  final double pBoundaryHeight ) {
        setAutoSync( pAutoSync );

        setExtents( pBoundaryX, pBoundaryY, pBoundaryWidth, pBoundaryHeight );
    }

    /*
     * Fully qualified pseudo-constructor. 
     */
    public void setDrawingLimits( final boolean pAutoSync, final Extents2D pExtents ) {
        setAutoSync( pAutoSync );

        setExtents( pExtents );
    }

    /*
     * Fully qualified pseudo-constructor. 
     */
    public void setDrawingLimits( final boolean pAutoSync, final Rectangle2D pRectangle ) {
        setAutoSync( pAutoSync );

        setExtents( pRectangle );
    }

    /*
     * Copy pseudo-constructor. 
     */
    public void setDrawingLimits( final DrawingLimits pDrawingLimits ) {
        setDrawingLimits( pDrawingLimits.isAutoSync(),
                          pDrawingLimits.getX(),
                          pDrawingLimits.getY(),
                          pDrawingLimits.getWidth(),
                          pDrawingLimits.getHeight() );
    }
}
