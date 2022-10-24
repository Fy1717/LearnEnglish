package com.raven.models;

import Pr22.DocumentReaderDevice;
import Pr22.ECard;
import Pr22.ECardHandling.AuthLevel;
import Pr22.ECardHandling.FileId;
import Pr22.Events.*;
import Pr22.Imaging.Light;
import Pr22.Imaging.PagePosition;
import Pr22.Processing.*;
import Pr22.Task.DocScannerTask;
import Pr22.Task.ECardTask;
import Pr22.Task.EngineTask;
import Pr22.Task.TaskControl;
import Pr22.Util.CompatEnum;
import PrIns.Exceptions.ErrorCodes;
import PrIns.Exceptions.General;
import PrIns.Exceptions.InvalidParameter;
import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Controller {
    private static Controller controller;
    private Controller() {}
    private static User user = User.getInstanceUser();

    private static Status status = Status.getInstanceStatus();

    public static Controller getInstanceController() throws General {
        if(controller == null) {
            controller = new Controller();
        }

        return controller;
    }
}
