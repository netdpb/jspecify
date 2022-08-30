/*
 * Copyright 2020 The JSpecify Authors.
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
abstract class UnrecognizedLocationsMisc {
  interface Super {}

  static class Sub
      // test:irrelevant_annotation
      extends @Nullable Object
      // test:irrelevant_annotation
      implements @Nullable Super {
    // test:irrelevant_annotation
    @Nullable Sub() {}
  }

  void foo() throws Exception {
    try {
      // test:irrelevant_annotation
      @Nullable Object o;

      @Nullable Object[] a0;

      // test:irrelevant_annotation
      Object @Nullable [] a1;

      // test:irrelevant_annotation
      @Nullable Object @Nullable [] a2;

      // test:irrelevant_annotation
    } catch (@Nullable Exception e) {
    }

    // test:irrelevant_annotation
    try (@Nullable AutoCloseable a = get()) {}
  }

  abstract AutoCloseable get();
}
