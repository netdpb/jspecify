/*
 * Copyright 2020 The jspecify Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jspecify.nullness.NullMarked;
import org.jspecify.nullness.Nullable;

@NullMarked
class BoundedTypeVariableReturn {
  Foo use(FooSupplier<?> supplier) {
    return supplier.get();
  }

  Foo use(NullableFooSupplier<?> supplier) {
    // test:cannot-convert:capture of ? extends Foo? to Foo!
    return supplier.get();
  }

  Lib<? extends Foo> use(LibOfNullableFooSupplier<?> supplier) {
    // test:cannot-convert:Lib!<capture of ? extends Foo?> to Lib!<? extends Foo!>
    return supplier.get();
  }

  interface FooSupplier<F extends Foo> {
    F get();
  }

  interface NullableFooSupplier<F extends @Nullable Foo> {
    F get();
  }

  interface LibOfNullableFooSupplier<F extends @Nullable Foo> {
    Lib<F> get();
  }

  interface Foo {}

  interface Lib<T extends @Nullable Object> {}
}
