package ru.sbtschool.patterns.report;

import ru.sbtschool.patterns.dto.ReportDepartmentResultDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * Created by Home on 16.08.2018.
 */
public class HtmlReporter {
    public static String toHtml( ReportDepartmentResultDto departmentResultDto ) {
        String[] columns = departmentResultDto.getColumns();

        // create a StringBuilder holding a resulting html
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append( "<html><body><table><tr>");

        for ( String column : columns )
            resultingHtml.append( surroundWith( "th", column ) );
        resultingHtml.append( "</tr>" );

        for ( Object[] row : departmentResultDto.getRows() ) {
            // process each row of query results
            resultingHtml.append( "<tr>" ); // add row start tag

            for ( int i = 0; i < columns.length; i++ ) {
                resultingHtml.append( surroundWith( "td", Objects.toString( row[ i ] ) ) );
            }

            resultingHtml.append( "</tr>" ); // add row end tag
        }

        resultingHtml.append( "<tr>" );
        for ( Object total[] : departmentResultDto.getTotals() ) {
            for ( int i = 0; i < columns.length; i++ ) {
                resultingHtml.append("<td>").append(total[i]).append("</td>");
            }
        }
        resultingHtml.append( "</tr>" );

        resultingHtml.append( "</table></body></html>" );

        return resultingHtml.toString();
    }

    private static String surroundWith( String tag, String text ) {
        return "<" + tag + ">" + text + "</" + tag + ">";
    }
}
