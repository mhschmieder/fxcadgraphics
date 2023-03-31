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

import java.util.Collection;
import java.util.HashSet;

/**
 * This is a utility class for Visual Aids -- especially actions on collections
 * that otherwise would require class derivation of GraphicalObjectCollection.
 */
public final class VisualAidUtilities {

    public static Collection< ArchitecturalVisualAid > getSelectedArchitecturalListenerPlanes( final GraphicalObjectCollection< ArchitecturalVisualAid > architecturalVisualAidCollection ) {
        // Get all of the selected Architectural Visual Aids that are marked as
        // Listener Planes.
        final Collection< ArchitecturalVisualAid > selectedArchitecturalListenerPlanes =
                                                                                       new HashSet<>( 20 );
        final Collection< ArchitecturalVisualAid > selectedArchitecturalVisualAids =
                                                                                   architecturalVisualAidCollection
                                                                                           .getSelection();
        selectedArchitecturalVisualAids.forEach( architecturalVisualAid -> {
            if ( architecturalVisualAid.isUseAsListenerPlane() ) {
                selectedArchitecturalListenerPlanes.add( architecturalVisualAid );
            }
        } );
        return selectedArchitecturalListenerPlanes;
    }

    public static Collection< MultilevelVisualAid > getSelectedMultilevelListenerPlanes( final GraphicalObjectCollection< MultilevelVisualAid > multilevelVisualAidCollection ) {
        // Get all of the selected Multilevel Visual Aids that are marked as
        // Listener Planes.
        final Collection< MultilevelVisualAid > selectedMultilevelListenerPlanes =
                                                                                 new HashSet<>( 20 );
        final Collection< MultilevelVisualAid > selectedMultilevelVisualAids =
                                                                             multilevelVisualAidCollection
                                                                                     .getSelection();
        selectedMultilevelVisualAids.forEach( multilevelVisualAid -> {
            if ( multilevelVisualAid.isUseAsListenerPlane() ) {
                selectedMultilevelListenerPlanes.add( multilevelVisualAid );
            }
        } );
        return selectedMultilevelListenerPlanes;
    }

    public static void selectAllArchitecturalListenerPlanes( final GraphicalObjectCollection< ArchitecturalVisualAid > architecturalVisualAidCollection ) {
        // Fill the Visual Aids selection set with all of the Visual Aids that
        // are marked as Listener Planes.
        final Collection< ArchitecturalVisualAid > collection = architecturalVisualAidCollection
                .getCollection();
        final Collection< ArchitecturalVisualAid > deselection = architecturalVisualAidCollection
                .getDeselection();

        // Fill the Visual Aids selection set with all of the Listener Planes.
        // NOTE: It is safer to avoid parallel streams right after clearing one
        // of the collections as a bulk action.
        collection.stream().filter( ArchitecturalVisualAid::isEditable )
                .forEach( architecturalVisualAid -> {
                    if ( architecturalVisualAid.isEditable()
                            && architecturalVisualAid.isUseAsListenerPlane() ) {
                        architecturalVisualAidCollection.addToSelection( architecturalVisualAid );
                        deselection.add( architecturalVisualAid );
                    }
                } );
    }

    public static void selectAllMultilevelListenerPlanes( final GraphicalObjectCollection< MultilevelVisualAid > multilevelVisualAidCollection ) {
        // Fill the Visual Aids selection set with all of the Visual Aids that
        // are marked as Listener Planes.
        final Collection< MultilevelVisualAid > collection = multilevelVisualAidCollection
                .getCollection();
        final Collection< MultilevelVisualAid > deselection = multilevelVisualAidCollection
                .getDeselection();

        // Fill the Visual Aids selection set with all of the Listener Planes.
        // NOTE: It is safer to avoid parallel streams right after clearing one
        // of the collections as a bulk action.
        collection.stream().filter( MultilevelVisualAid::isEditable )
                .forEach( multilevelVisualAid -> {
                    if ( multilevelVisualAid.isEditable()
                            && multilevelVisualAid.isUseAsListenerPlane() ) {
                        multilevelVisualAidCollection.addToSelection( multilevelVisualAid );
                        deselection.add( multilevelVisualAid );
                    }
                } );
    }
}