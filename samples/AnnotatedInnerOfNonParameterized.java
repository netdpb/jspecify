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
abstract class AnnotatedInnerOfNonParameterized {
  interface Lib<T extends @Nullable Object> {}

  class Nested {
    class DoublyNested {}
  }

  void foo(
      @Nullable Nested x4,

      // test:irrelevant_annotation
      @Nullable AnnotatedInnerOfNonParameterized.Nested x5,
      AnnotatedInnerOfNonParameterized.@Nullable Nested x6,

      // test:irrelevant_annotation
      @Nullable AnnotatedInnerOfNonParameterized.Nested.DoublyNested x7,

      // test:irrelevant_annotation
      AnnotatedInnerOfNonParameterized.@Nullable Nested.DoublyNested x8,
      AnnotatedInnerOfNonParameterized.Nested.@Nullable DoublyNested x9,

      // test:irrelevant_annotation
      Lib<@Nullable AnnotatedInnerOfNonParameterized.Nested.DoublyNested> l1,

      // test:irrelevant_annotation
      Lib<AnnotatedInnerOfNonParameterized.@Nullable Nested.DoublyNested> l2,
      Lib<AnnotatedInnerOfNonParameterized.Nested.DoublyNested> l3) {}

  Nested.DoublyNested create(Nested n) {
    return n.new DoublyNested();
  }

  // test:irrelevant_annotation
  abstract @Nullable AnnotatedInnerOfNonParameterized.Nested returnType();
}
