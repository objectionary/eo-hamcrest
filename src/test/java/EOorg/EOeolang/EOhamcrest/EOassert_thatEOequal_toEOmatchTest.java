/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Graur Andrew
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
// @checkstyle PackageNameCheck (1 line)
package EOorg.EOeolang.EOhamcrest;

import java.util.stream.Stream;
import org.eolang.Data;
import org.eolang.Dataized;
import org.eolang.PhMethod;
import org.eolang.PhWith;
import org.eolang.Phi;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Assert-that.equal-to.match.Test.
 *
 * @since 0.1
 * @checkstyle TypeNameCheck (100 lines)
 */
public final class EOassert_thatEOequal_toEOmatchTest {

    @ParameterizedTest
    @MethodSource("provideArgumentsForTests")
    public void arePrimitivesEqualTo(final Object actual, final Object expected) {
        final Phi ast = new PhWith(
            new PhWith(
                new PhWith(
                    new EOassert_that(Phi.Φ),
                   "actual",
                   new Data.ToPhi(actual)
                ), "matcher",
                new EOassert_that$EOequal_to$EOmatch(new EOassert_that$EOequal_to(Phi.Φ))
            ), "reasons",
            new Data.ToPhi("test reason")
        );
        final Phi eqt = new PhWith(
            new PhMethod(ast, "equal-to"),
            "x",
            new Data.ToPhi(expected)
        );
        MatcherAssert.assertThat(
            new Dataized(
                new PhWith(
                    new PhMethod(eqt, "match"),
                    "a",
                    new Data.ToPhi(actual)
                )
            ).take(Boolean.class),
            Matchers.is(true)
        );
    }

    private static Stream<Arguments> provideArgumentsForTests() {
        return Stream.of(
            Arguments.of("text", "text"),
            Arguments.of(150L, 150L),
            Arguments.of(true, true),
            Arguments.of('c', 'c'),
            Arguments.of(2.18d, 2.18d),
            Arguments.of(
                new byte[] {(byte) 0x00, (byte) 0x1f},
                new byte[] {(byte) 0x00, (byte) 0x1f}
            )
        );
    }
}
