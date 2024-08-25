/*
 *  Copyright 2024 lasse-cs.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.jso.popover;

import org.teavm.jso.JSProperty;
import org.teavm.jso.dom.html.HTMLElement;

public interface PopoverInvokerElement {
    
    String HIDE = "hide";
    
    String SHOW = "show";

    String TOGGLE = "toggle";

    @JSProperty
    String getPopoverTargetAction();

    @JSProperty
    void setPopoverTargetAction(String action);

    @JSProperty
    HTMLElement getPopoverTargetElement();

    @JSProperty
    void setPopoverTargetElement(HTMLElement popoverTarget);
}