package com.imdadareeph.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Mohd Imdad Areeph
 *
 */
@Slf4j
@RequiredArgsConstructor
@Component
public final class ApplicationUtil
{
    /**
     * A composed predicate that represents a StringUtils.equalsIgnoreCase.
     */
    public static final BiPredicate<String, String> areBothEqual = StringUtils::equalsIgnoreCase;

    public static final Predicate<String> isBlank = StringUtils::isBlank;

    public static final Predicate<Integer> validateUpdate = (value) -> value >0 ? TRUE: FALSE;

    public static final Predicate<Set> isNotEmpty = CollectionUtils::isNotEmpty;

    /**
     * Returns a composed predicate of areBothEqual.
     *
     * @param value to be validated.
     * @return a {@code Predicate<String>} Returns a composed predicate.
     */
    public static Predicate<String> areBothEqualPredicate(final String value) {
        return str -> areBothEqual.test(str, value);
    }

}
