package testsuite.locales

import locales.DecimalFormatUtil
import org.junit.Test
import org.junit.Assert._

class DecimalFormatUtilTest {
  @Test def test_decimal_patterns(): Unit = {
    val p1 = DecimalFormatUtil.toDecimalPatterns("#,##0.00;(#,##0.00)")
    assertEquals("#,##0.00", p1.positive.pattern)
    assertEquals("", p1.positive.prefix)
    assertEquals("", p1.positive.suffix)

    assertEquals(Some("#,##0.00"), p1.negative.map{ _.pattern })
    assertEquals(Some("("), p1.negative.map{ _.prefix })
    assertEquals(Some(")"), p1.negative.map{ _.suffix })

    val p2 = DecimalFormatUtil.toDecimalPatterns("#,##0.00")
    assertEquals("", p2.positive.prefix)
    assertEquals("#,##0.00", p2.positive.pattern)
    assertEquals("", p2.positive.suffix)
    assertTrue(p2.negative.isEmpty)

    val p3 = DecimalFormatUtil.toDecimalPatterns("")
    assertEquals("", p3.positive.prefix)
    assertEquals("", p3.positive.pattern)
    assertEquals("", p3.positive.suffix)
    assertTrue("", p3.negative.isEmpty)

    val p4 = DecimalFormatUtil.toDecimalPatterns("ABC #,##0.00 cde")
    assertEquals("ABC ", p4.positive.prefix)
    assertEquals("#,##0.00", p4.positive.pattern)
    assertEquals(" cde", p4.positive.suffix)
    assertTrue(p4.negative.isEmpty)

    val p5 = DecimalFormatUtil.toDecimalPatterns("\u2030 #,##0.00 %")
    assertEquals("\u2030 ", p5.positive.prefix)
    assertEquals("#,##0.00", p5.positive.pattern)
    assertEquals(" %", p5.positive.suffix)
    assertTrue(p5.negative.isEmpty)

    val p6 = DecimalFormatUtil.toDecimalPatterns("'' #,##0.00")
    assertEquals("' ", p6.positive.prefix)
    assertEquals("#,##0.00", p6.positive.pattern)
    assertEquals("", p6.positive.suffix)
    assertTrue(p6.negative.isEmpty)

    val p7 = DecimalFormatUtil.toDecimalPatterns("'#' #,##0.00")
    assertEquals("# ", p7.positive.prefix)
    assertEquals("#,##0.00", p7.positive.pattern)
    assertEquals("", p7.positive.suffix)
    assertTrue(p7.negative.isEmpty)

    val p8 = DecimalFormatUtil.toDecimalPatterns("###,#'#'")
    assertEquals("", p8.positive.prefix)
    assertEquals("###,#", p8.positive.pattern)
    assertEquals("#", p8.positive.suffix)
    assertTrue(p8.negative.isEmpty)
  }
}
