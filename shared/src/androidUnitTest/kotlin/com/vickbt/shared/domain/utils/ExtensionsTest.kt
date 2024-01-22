package com.vickbt.shared.domain.utils

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class ExtensionsTest {

    @Test
    fun `toReadableFormat returns correct date`() = runTest {
        val result = LocalDateTime.parse("2024-01-20T12:24:58")
        val expectedResult = "Saturday, 20 January 2024"

        assertEquals(expected = expectedResult, actual = result.toReadableFormat())
    }

    @Test
    fun `capitalizeEachWord formats lowercase sentence to titleCase`() = runTest {
        val result = "this is a test"
        val expectedResult = "This Is A Test"

        assertEquals(expected = expectedResult, actual = result.capitalizeEachWord())
    }

    @Test
    fun `capitalizeEachWord formats uppercase sentence to titleCase`() = runTest {
        val result = "THIS IS A TEST"
        val expectedResult = "This Is A Test"

        assertEquals(expected = expectedResult, actual = result.capitalizeEachWord())
    }

    @Test
    fun `toTempUnitOfMeasurement appends degree celsius when using metric system`() = runTest {
        val result = 24.8.toTempUnitOfMeasurement(unitOfMeasurement = MeasurementOptions.METRIC)

        assertEquals(expected = "24°C", actual = result)
    }

    @Test
    fun `toTempUnitOfMeasurement appends degree fahrenheit when using imperial system`() = runTest {
        val result = 24.8.toTempUnitOfMeasurement(unitOfMeasurement = MeasurementOptions.IMPERIAL)

        assertEquals(expected = "24°F", actual = result)
    }

    @Test
    fun `toSpeedUnitOfMeasurement appends kilometer per hour when using metric system`() = runTest {
        val result = 80.4.toSpeedUnitOfMeasurement(unitOfMeasurement = MeasurementOptions.METRIC)

        assertEquals(expected = "80 km/h", actual = result)
    }

    @Test
    fun `toSpeedUnitOfMeasurement appends miles per hour when using imperial system`() =
        runTest {
            val result =
                80.4.toSpeedUnitOfMeasurement(unitOfMeasurement = MeasurementOptions.IMPERIAL)

            assertEquals(expected = "80 m/h", actual = result)
        }
}
