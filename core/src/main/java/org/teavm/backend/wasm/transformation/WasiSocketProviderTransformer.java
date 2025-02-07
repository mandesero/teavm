/*
 *  Copyright 2022 Alexey Andreev.
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
package org.teavm.backend.wasm.transformation;

import org.teavm.backend.wasm.runtime.net.WasiVirtualSocket;
import org.teavm.model.ClassHolderTransformerContext;
import org.teavm.model.MethodHolder;
import org.teavm.model.emit.ProgramEmitter;
import org.teavm.runtime.net.VirtualSocketProviderTransformer;

public class WasiSocketProviderTransformer extends VirtualSocketProviderTransformer {
    @Override
    protected void transformCreateMethod(MethodHolder method, ClassHolderTransformerContext context) {
        ProgramEmitter pe = ProgramEmitter.create(method, context.getHierarchy());
        pe.construct(WasiVirtualSocket.class).returnValue();
    }
}
