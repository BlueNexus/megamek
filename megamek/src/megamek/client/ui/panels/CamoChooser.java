/*
 * Copyright (c) 2020-2021 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMek.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megamek.client.ui.panels;

import megamek.client.ui.swing.tileset.MMStaticDirectoryManager;
import megamek.client.ui.swing.util.PlayerColour;
import megamek.client.ui.trees.CamoChooserTree;
import megamek.common.annotations.Nullable;
import megamek.common.icons.AbstractIcon;
import megamek.common.icons.Camouflage;
import megamek.common.util.fileUtils.AbstractDirectory;

import java.util.ArrayList;
import java.util.List;

public class CamoChooser extends AbstractIconChooser {
    //region Variable Declarations
    private boolean canHaveIndividualCamouflage;
    //endregion Variable Declarations

    //region Constructors
    public CamoChooser(final @Nullable AbstractIcon camouflage, final boolean canHaveIndividualCamouflage) {
        super(new CamoChooserTree(), camouflage);
        setCanHaveIndividualCamouflage(canHaveIndividualCamouflage);
    }
    //endregion Constructors

    //region Getters/Setters
    public boolean canHaveIndividualCamouflage() {
        return canHaveIndividualCamouflage;
    }

    public void setCanHaveIndividualCamouflage(final boolean canHaveIndividualCamouflage) {
        this.canHaveIndividualCamouflage = canHaveIndividualCamouflage;
    }
    //endregion Getters/Setters

    @Override
    protected @Nullable AbstractDirectory getDirectory() {
        return MMStaticDirectoryManager.getCamouflage();
    }

    @Override
    protected AbstractIcon createIcon(String category, final String filename) {
        return new Camouflage(category, filename);
    }

    @Override
    protected List<AbstractIcon> getIcons(final String category) {
        if (category.startsWith(Camouflage.COLOUR_CAMOUFLAGE)) {
            final List<AbstractIcon> icons = new ArrayList<>();
            // This section is a list of all colour camouflages supported
            for (PlayerColour colour : PlayerColour.values()) {
                icons.add(createIcon(Camouflage.COLOUR_CAMOUFLAGE, colour.name()));
            }
            return icons;
        } else {
            return super.getIcons(category);
        }
    }

    @Override
    public void refreshDirectory() {
        MMStaticDirectoryManager.refreshCamouflageDirectory();
        refreshDirectory(new CamoChooserTree());
    }
}
