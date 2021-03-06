//=============================================================================
// Copyright 2006-2013 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================
package com.ge.DAP.utilities;

import java.util.Comparator;
import org.testng.IClass;

/**
 * Comparator for sorting classes alphabetically by fully-qualified name.
 * @author Daniel Dyer
 */
class TestClassComparator implements Comparator<IClass>
{
    public int compare(IClass class1, IClass class2)
    {
        return class1.getName().compareTo(class2.getName());
    }
}
