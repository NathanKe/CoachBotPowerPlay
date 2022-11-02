package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

public class OmniHolonomicCalculation {
    public double power_fl;
    public double power_fr;
    public double power_bl;
    public double power_br;

    public double low_pow_pct;
    public double std_pow_pct;
    public double hgh_pow_pct;

    public OmniHolonomicCalculation(double in_low, double in_std, double in_hgh){
        low_pow_pct = in_low;
        std_pow_pct = in_std;
        hgh_pow_pct = in_hgh;

        power_fl = 0.0;
        power_fr = 0.0;
        power_bl = 0.0;
        power_br = 0.0;
    }

    private double calcTriggerScale(double left_trigger, double right_trigger){
        // if both pulled, default to standard
        if(left_trigger > 0.0 && right_trigger > 0.0){
            return std_pow_pct;
        }
        // else handle left for slow
        else if( left_trigger > 0.0) {
            return (low_pow_pct - std_pow_pct) * left_trigger + std_pow_pct;
        }
        // else handle right for turbo
        else if(right_trigger > 0.0){
            return (hgh_pow_pct - std_pow_pct) * right_trigger + std_pow_pct;
        }
        // else neither, default to standard
        else{
            return std_pow_pct;
        }
    }

    private double adjust_X_toGyro(double x, double y, double yaw_err){
        return x * Math.cos(yaw_err) - y * Math.sin(yaw_err);
    }

    private double adjust_Y_toGyro(double x, double y, double yaw_err){
        return x * Math.sin(yaw_err) + y * Math.cos(yaw_err);
    }

    public void reCalcPower(Gamepad gp, double yaw_error){
        reCalcPower(gp.left_stick_x, gp. left_stick_y, gp.right_stick_x, gp.left_trigger, gp.right_trigger, yaw_error);
    }

    public void reCalcPower(double x, double y, double t, double l_tr, double r_tr, double yaw_error){
        double triggerScaleFactor = calcTriggerScale(l_tr, r_tr);

        double inBoundsScaleFactor = Math.max(1, Math.abs(y)+Math.abs(x) + Math.abs(t));

        double combinedScaleFactor = triggerScaleFactor/inBoundsScaleFactor;

        double adj_x = adjust_X_toGyro(x, y, yaw_error);
        double adj_y = adjust_Y_toGyro(x, y, yaw_error);

        power_fl = combinedScaleFactor * (adj_y + adj_x + t);
        power_fr = combinedScaleFactor * (-adj_y + adj_x + t);
        power_bl = combinedScaleFactor * (adj_y - adj_x + t);
        power_br = combinedScaleFactor * (-adj_y - adj_x + t);
    }
}
