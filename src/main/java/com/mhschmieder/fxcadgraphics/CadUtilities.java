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

import com.mhschmieder.commonstoolkit.text.TextUtilities;
import javafx.collections.ObservableList;

import java.text.NumberFormat;

/**
 * This is a utility class for CAD oriented functionality.
 */
public final class CadUtilities {

    public static String getSurfaceNameDefault( final int surfaceNumber ) {
        final String surfaceNameDefault = "Surface " + Integer.toString( surfaceNumber ); //$NON-NLS-1$
        return surfaceNameDefault;
    }

    public static String getSurfaceNameDefault( final SurfaceProperties surfaceProperties ) {
        final int surfaceNumber = surfaceProperties.getSurfaceNumber();
        return getSurfaceNameDefault( surfaceNumber );
    }

    // Get a unique Surface Name from the candidate name.
    public static String getUniqueSurfaceName( final ObservableList< SurfaceProperties > surfacePropertiesList,
                                               final SurfaceProperties surfacePropertiesToExclude,
                                               final String surfaceNameCandidate,
                                               final int uniquefierNumber,
                                               final NumberFormat uniquefierNumberFormat ) {
        // Recursively search for (and enforce) name-uniqueness of the supplied
        // Surface Name candidate and uniquefier number.
        final String uniquefierAppendix = TextUtilities
                .getUniquefierAppendix( uniquefierNumber, uniquefierNumberFormat );
        String uniqueSurfaceName = surfaceNameCandidate + uniquefierAppendix;
        if ( !isSurfaceNameUnique( surfacePropertiesList,
                                   surfacePropertiesToExclude,
                                   uniqueSurfaceName ) ) {
            // Recursively guarantee the appendix-adjusted name is also unique,
            // using a hopefully-unique number as the appendix.
            uniqueSurfaceName = getUniqueSurfaceName( surfacePropertiesList,
                                                      surfacePropertiesToExclude,
                                                      surfaceNameCandidate,
                                                      uniquefierNumber + 1,
                                                      uniquefierNumberFormat );
        }

        return uniqueSurfaceName;
    }

    public static String getUniqueSurfaceName( final ObservableList< SurfaceProperties > surfacePropertiesList,
                                               final SurfaceProperties surfacePropertiesToExclude,
                                               final String surfaceNameCandidate,
                                               final NumberFormat uniquefierNumberFormat ) {
        final String surfaceNameDefault = getSurfaceNameDefault( surfacePropertiesToExclude );
        final String uniqueSurfaceName = getUniqueSurfaceName( surfacePropertiesList,
                                                               surfacePropertiesToExclude,
                                                               surfaceNameCandidate,
                                                               surfaceNameDefault,
                                                               uniquefierNumberFormat );
        return uniqueSurfaceName;
    }

    // Get a unique Surface Name from the candidate name.
    public static String getUniqueSurfaceName( final ObservableList< SurfaceProperties > surfacePropertiesList,
                                               final SurfaceProperties surfacePropertiesToExclude,
                                               final String surfaceNameCandidate,
                                               final String surfaceNameDefault,
                                               final NumberFormat uniquefierNumberFormat ) {
        // Try to use the specified Surface Name if it exists and is non-empty;
        // otherwise apply the pre-assigned default name for the current
        // Surface, leaving unadorned if possible.
        final int uniquefierNumber = 0;
        final String surfaceNameCandidateAdjusted = ( surfaceNameCandidate == null )
                || surfaceNameCandidate.trim().isEmpty()
                    ? surfaceNameDefault
                    : surfaceNameCandidate;
        final String uniqueSurfaceName = getUniqueSurfaceName( surfacePropertiesList,
                                                               surfacePropertiesToExclude,
                                                               surfaceNameCandidateAdjusted,
                                                               uniquefierNumber,
                                                               uniquefierNumberFormat );
        return uniqueSurfaceName;
    }

    public static boolean isSurfaceNameUnique( final ObservableList< SurfaceProperties > surfacePropertiesList,
                                               final int surfaceToExcludeIndex,
                                               final String surfaceNameCandidate ) {
        // Iterate through all of the Surfaces, to see if any use the proposed
        // Surface Name.
        boolean surfaceNameUnique = true;

        for ( int surfaceIndex = 0; surfaceIndex < Region2D.NUMBER_OF_SURFACES; surfaceIndex++ ) {
            // Skip the Surface if at the reference Surface's index.
            if ( surfaceIndex == surfaceToExcludeIndex ) {
                continue;
            }

            // Check for a naming collision of Surface Names.
            final SurfaceProperties surfaceProperties = surfacePropertiesList.get( surfaceIndex );
            final String surfaceName = surfaceProperties.getSurfaceName();
            if ( surfaceName.equals( surfaceNameCandidate ) ) {
                surfaceNameUnique = false;
                break;
            }
        }

        return surfaceNameUnique;
    }

    public static boolean isSurfaceNameUnique( final ObservableList< SurfaceProperties > surfacePropertiesList,
                                               final SurfaceProperties surfacePropertiesToExclude,
                                               final String surfaceNameCandidate ) {
        final int surfaceToExcludeIndex = surfacePropertiesToExclude.getSurfaceNumber() - 1;
        final boolean surfaceNameUnique = isSurfaceNameUnique( surfacePropertiesList,
                                                               surfaceToExcludeIndex,
                                                               surfaceNameCandidate );

        return surfaceNameUnique;
    }
}
