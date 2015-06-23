package carlosdelachica.com.designsupport;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.ColorInt;

public class ColorCalculator {

    private Context context;
    private int lastDarkColor;
    private int lastBrightColor;
    private int lastAccentColor;

    public ColorCalculator(Context context) {
        this.context = context;
    }

    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private int[] colors = new int[]{R.color.primary, R.color.tinting1, R.color.tinting2};
    private int[] darkColors = new int[]{R.color.primary_dark, R.color.tinting1dark, R.color.tinting2dark};
    private int[] accentColors = new int[]{R.color.accent, R.color.tinting1accent, R.color.tinting2accent};

    @ColorInt
    public int calculateDarkColor(float positionOffset, int position) {
        if (position != colors.length - 1) {
            lastDarkColor = calculateColor(positionOffset,
                    context.getResources().getColor(darkColors[position]),
                    context.getResources().getColor(darkColors[position + 1]));
            return lastDarkColor;
        }
        return lastDarkColor;
    }

    @ColorInt
    public int calculateBrightColor(float positionOffset, int position) {
        if (position != colors.length - 1) {
            lastBrightColor = calculateColor(positionOffset,
                    context.getResources().getColor(colors[position]),
                    context.getResources().getColor(colors[position + 1]));
            return lastBrightColor;
        }
        return lastBrightColor;
    }

    @ColorInt
    public int calculateAccentColor(float positionOffset, int position) {
        if (position != colors.length - 1) {
            lastAccentColor = calculateColor(positionOffset,
                    context.getResources().getColor(accentColors[position]),
                    context.getResources().getColor(accentColors[position + 1]));
            return lastAccentColor;
        }
        return lastAccentColor;
    }

    private int calculateColor(float positionOffset, int color, int color2) {
        return (int) argbEvaluator.evaluate(
                positionOffset,
                color,
                color2);
    }

}