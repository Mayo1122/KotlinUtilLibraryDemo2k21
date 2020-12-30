package com.macrohard.androidutils

import android.text.format.DateUtils
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * format date with DateFormat string
 *
 * @param[format] optional. default is yyyy-MM-dd HH:mm:ss (2017-06-02 19:20:00)
 * @return Formatted Date
 */
@JvmOverloads
fun Date.asDateString(format: String? = "yyyy-MM-dd HH:mm:ss"): String = SimpleDateFormat(format, Locale.getDefault()).format(this)

/**
 * get readable string of given timestamp.
 *
 * @param[format] optional. default is yyyy-MM-dd HH:mm:ss (2017-06-02 19:20:00)
 * @return Formatted Date
 */
@JvmOverloads
fun Long.asDateString(format: String? = "yyyy-MM-dd HH:mm:ss"): String = Date(this).asDateString(format)

/**
 * parsing date from String
 *
 * @param[format] optional. default is yyyy-MM-dd HH:mm:ss (2017-06-02 19:20:00)
 * @return Date object, Nullable
 */
@JvmOverloads
fun String.parseDate(format: String? = "yyyy-MM-dd HH:mm:ss"): Date? = try {
    SimpleDateFormat(format, Locale.getDefault()).parse(this)
} catch (e: Exception) {
    null
}

/**
 * generate Date String in currentTImeMillis
 *
 * @param[format] optional. default is yyyy-MM-dd HH:mm:ss (2017-06-02 19:20:00)
 */
@JvmOverloads
fun nowDateString(format: String? = "yyyy-MM-dd HH:mm:ss") = System.currentTimeMillis().asDateString(format)

/**
 * format formatted date to another formatted date
 *
 * @param[fromFormat] original date format
 * @param[toFormat] to convert
 * @return new Formatted Date
 */
fun String.toDateString(fromFormat: String, toFormat: String): String {
    val result: String
    val df = SimpleDateFormat(fromFormat, Locale.getDefault())
    val df2 = SimpleDateFormat(toFormat, Locale.getDefault())
    try {
        result = df2.format(df.parse(this))
    } catch (e: ParseException) {
        return this
    }
    return result
}

/**
 * normalize the date to the beginning of the day with given timeZone
 * it equals to Time.getJulianDay()
 *
 * @param timeZone optional, default is "UTC"
 */
@JvmOverloads
fun Long.normalizeDate(timeZone: String = "UTC"): Long {
    val date = GregorianCalendar.getInstance(TimeZone.getTimeZone(timeZone)) as GregorianCalendar
    date.time = Date(this)
    date.set(Calendar.HOUR_OF_DAY, 0)
    date.set(Calendar.MINUTE, 0)
    date.set(Calendar.SECOND, 0)
    date.set(Calendar.MILLISECOND, 0)

    return date.timeInMillis
}

/**
 * normalize the date to the beginning of the day with given timeZone
 * it equals to Time.getJulianDay()
 *
 * @param timeZone optional, default is "UTC"
 */
@JvmOverloads
fun Date.normalizeDate(timeZone: String = "UTC"): Date = Date(this.time.normalizeDate(timeZone))

/**
 * get TimeZone on system
 */
fun getTimeZone() = TimeZone.getDefault()

/**
 * get id of TimeZone on system
 */
fun getTimeZoneId() = TimeZone.getDefault().id

fun timestamp(): Long = System.currentTimeMillis()

fun Date.asString(format: DateFormat): String = format.format(this)

fun Date.asString(format: String): String = asString(SimpleDateFormat(format, Locale.US))




/**
 * Converts the timestamp in millis to date
 * @return Date object with the given timestamp in millis
 */
fun Long.toDate(): Date = Date(this)

/**
 * Gets the calendar instance with the given timestamp in millis
 * @return Calendar instance with the give timestamp in millis
 */
fun Long.toCalendar(): Calendar = Calendar.getInstance().apply { timeInMillis = this@toCalendar }

/**
 * Converts the timestamp to relative time string
 * @return Returns the relative time span string
 */
fun Long.getRelativeTimeSpanString() = DateUtils.getRelativeTimeSpanString(this)


/**
 * Formats the calendar instance to the given output date format
 * @param format Date format excepted by SimpleDateFormat
 * @param locale An optional locale instance to consider while formatting date
 * @return A formatted date string
 */
fun Calendar.format(format: String, locale: Locale = Locale.US) = SimpleDateFormat(format, locale).format(time)


/**
 * Formats the date instance to the given output date format
 * @param format Date format excepted by SimpleDateFormat
 * @param locale An optional locale instance to consider while formatting date
 * @return A formatted date string
 */
fun Date.format(format: String, locale: Locale = Locale.US) = SimpleDateFormat(format, locale).format(this)

/**
 * Formats the timestamp to the given output date format
 * @param format Date format excepted by SimpleDateFormat
 * @param locale An optional locale instance to consider while formatting date
 * @return A formatted date string
 */
fun Long.formatToDate(format: String, locale: Locale = Locale.US) = SimpleDateFormat(format, locale).format(this.toDate())