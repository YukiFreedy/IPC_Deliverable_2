/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable2;

import java.awt.RenderingHints;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import jgpx.model.analysis.Chunk;
import jgpx.model.analysis.TrackData;
import jgpx.model.gpx.Track;
import jgpx.model.jaxb.GpxType;
import jgpx.model.jaxb.TrackPointExtensionT;
import jgpx.util.DateTimeUtils;

/**
 * FXML Controller class
 *
 * @author Yuki
 */
public class MainWindowController implements Initializable {

    @FXML
    private TextField startDate;
    @FXML
    private TextField startTime;
    @FXML
    private TextField duration;
    @FXML
    private TextField exerciseTime;
    @FXML
    private TextField totalDistance;
    @FXML
    private TextField slopeUp;
    @FXML
    private TextField maxSpeed;
    @FXML
    private TextField slopeDown;
    @FXML
    private TextField avSpeed;
    @FXML
    private Button loadButton;

    private TrackData trackData;
    @FXML
    private TextField maxHeart;
    @FXML
    private TextField minHeart;
    @FXML
    private TextField maxPedaling;
    @FXML
    private TextField avPedaling;

    private AreaChart<Number, Number> heightDistChart;

    private LineChart<Number, Number> someDistChart;

    @FXML
    private RadioButton speedDist;
    @FXML
    private RadioButton hrDist;
    @FXML
    private RadioButton pedalingDist;
    @FXML
    private RadioButton speedTime;
    @FXML
    private RadioButton hrTime;
    @FXML
    private RadioButton pedalingTime;
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Number> barChart;

    private GpxType gpx;
    @FXML
    private GridPane gridDistance;
    @FXML
    private GridPane timeGrid;

    private LineChart<Number, Number> heightTimeChart;

    private LineChart<Number, Number> someTimeChart;

    private File file;
    @FXML
    private RadioButton LastMonth;
    @FXML
    private RadioButton ThreeLastMonths;
    @FXML
    private ProgressIndicator progress;

    private Task<help> taskDistance;

    private Task<help> taskTime;

    private Task<help> taskGlobal1;

    private Task<help> taskMulti;

    private Task<help2> taskPie;

    @FXML
    private TextField textAge;

    private double count;
    private double sum;

    private MainWindowController controller;

    public void initData(MainWindowController a) {
        controller = a;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        finishProgress();

        if (heightDistChart != null) {
            gridDistance.getChildren().remove(heightDistChart);
        }
        NumberAxis xAxis = new NumberAxis();
        xAxis.setTickUnit(10);
        xAxis.setMinorTickCount(0);
        xAxis.setAutoRanging(true);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickUnit(200);
        yAxis.setTickMarkVisible(false);
        heightDistChart
                = new AreaChart<>(xAxis, yAxis);
        heightDistChart.setTitle("Distance X Height");
        heightDistChart.setCreateSymbols(false);
        heightDistChart.setLegendVisible(false);

        gridDistance.add(heightDistChart, 0, 0);

        if (heightTimeChart != null) {
            timeGrid.getChildren().remove(heightTimeChart);
        }
        NumberAxis xAxis2 = new NumberAxis();
        xAxis.setTickUnit(10);
        xAxis.setMinorTickCount(0);
        xAxis.setAutoRanging(true);
        NumberAxis yAxis2 = new NumberAxis();
        yAxis.setTickUnit(200);
        yAxis.setTickMarkVisible(false);
        heightTimeChart
                = new LineChart<>(xAxis2, yAxis2);
        heightTimeChart.setTitle("Time x Height");
        heightTimeChart.setCreateSymbols(false);
        heightTimeChart.setLegendVisible(false);

        timeGrid.add(heightTimeChart, 0, 0);

        xAxis = new NumberAxis();
        xAxis.setTickUnit(10);
        xAxis.setMinorTickCount(0);
        xAxis.setAutoRanging(true);
        yAxis = new NumberAxis();
        yAxis.setTickUnit(200);
        yAxis.setTickMarkVisible(false);

        someDistChart
                = new LineChart<>(xAxis, yAxis);
        someDistChart.setCreateSymbols(false);
        someDistChart.setLegendVisible(true);
        gridDistance.add(someDistChart, 0, 1);

        xAxis = new NumberAxis();
        xAxis.setTickUnit(10);
        xAxis.setMinorTickCount(0);
        xAxis.setAutoRanging(true);
        yAxis = new NumberAxis();
        yAxis.setTickUnit(200);
        yAxis.setTickMarkVisible(false);
        someTimeChart
                = new LineChart<>(xAxis, yAxis);
        someTimeChart.setCreateSymbols(false);
        someTimeChart.setLegendVisible(true);
        timeGrid.add(someTimeChart, 0, 1);

        taskDistance = new Task<help>() {
            @Override
            protected help call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        taskTime = new Task<help>() {
            @Override
            protected help call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        taskPie = new Task<help2>() {
            @Override
            protected help2 call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        taskMulti = new Task<help>() {
            @Override
            protected help call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        taskGlobal1 = new Task<help>() {
            @Override
            protected help call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

    }

    private void sumProgress(double sum) {
        progress.setProgress(progress.getProgress() + sum);
    }

    private void startProgress() {
        progress.setVisible(true);
        progress.setProgress(0);
    }

    private void finishProgress() {
        progress.setVisible(false);
        progress.setProgress(1.0);
    }

    public void loadChart() {

        ObservableList<Chunk> chunks = trackData.getChunks();

        taskGlobal1 = new Task<help>() {
            @Override
            protected help call() throws Exception {
                startProgress();
                double count = chunks.size() * 2;
                double sum = 1 / count;
                startProgress();

                XYChart.Series first = new XYChart.Series();

                double dist = 0.0;
                for (int i = 0; i < chunks.size(); i++) {
                    if (i != 0) {
                        dist += chunks.get(i).getDistance();
                    }
                    first.getData().add(new XYChart.Data(dist, chunks.get(i).getLastPoint().getElevation()));
                    controller.sumProgress(sum);
                }
                help a = new help();
                ArrayList<XYChart.Series> aux = new ArrayList<>();
                aux.add(first);

                XYChart.Series sec = new XYChart.Series();

                dist = 0.0;
                for (int i = 0; i < chunks.size(); i++) {
                    if (i != 0) {
                        dist += chunks.get(i).getDuration().getSeconds();
                    }
                    sec.getData().add(new XYChart.Data(dist, chunks.get(i).getLastPoint().getElevation()));
                    controller.sumProgress(sum);
                }
                aux.add(sec);
                a.setData(aux);

                return a;
            }
        ;
        };
        
        taskGlobal1.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                finishProgress();
                try {
                    heightDistChart.getData().setAll(taskGlobal1.get().getData().get(0));

                    heightTimeChart.getData().setAll(taskGlobal1.get().getData().get(1));
                } catch (InterruptedException | ExecutionException e) {

                }
            }
        });

        Thread th = new Thread(taskGlobal1);
        th.setDaemon(true);
        th.start();

    }

    @FXML
    private void onOpen(ActionEvent event) throws JAXBException {
        if (taskDistance.isRunning() || taskGlobal1.isRunning() || taskMulti.isRunning() || taskPie.isRunning() || taskTime.isRunning()) {
            return;
        }

        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(loadButton.getScene().getWindow());
        if (file == null) {
            return;
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class, TrackPointExtensionT.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<Object> root = (JAXBElement<Object>) unmarshaller.unmarshal(file);
        gpx = (GpxType) root.getValue();

        if (gpx != null) {
            trackData = new TrackData(new Track(gpx.getTrk().get(0)));
            startDate.setText(DateTimeUtils.format(trackData.getStartTime()).split(" ")[0]);
            startTime.setText(DateTimeUtils.format(trackData.getStartTime()).split(" ")[1]);
            duration.setText(DateTimeUtils.format(trackData.getTotalDuration()));
            exerciseTime.setText(DateTimeUtils.format(trackData.getMovingTime()));
            totalDistance.setText(String.format("%.0f", trackData.getTotalDistance()));
            slopeUp.setText(String.format("%.2f", trackData.getTotalAscent()));
            slopeDown.setText(String.format("%.2f", trackData.getTotalDescend()));
            maxSpeed.setText(String.format("%.2f", trackData.getMaxSpeed()));
            avSpeed.setText(String.format("%.2f", trackData.getAverageSpeed()));
            maxHeart.setText(String.format("%d", trackData.getMaxHeartrate()));
            minHeart.setText(String.format("%d", trackData.getMinHeartRate()));
            //avHeart.setText(String.format("%d", trackData.getAverageHeartrate()));
            maxPedaling.setText(String.format("%d", trackData.getMaxCadence()));
            avPedaling.setText(String.format("%d", trackData.getAverageCadence()));

            loadChart();
        }
    }

    @FXML
    private void ondxs(ActionEvent event) {
        if (taskDistance.isRunning() || taskGlobal1.isRunning() || taskMulti.isRunning() || file == null) {
            return;
        }
        loadDistance();
    }

    @FXML
    private void ondxhr(ActionEvent event) {
        if (taskDistance.isRunning() || taskGlobal1.isRunning() || taskMulti.isRunning() || file == null) {
            return;
        }

        loadDistance();
    }

    @FXML
    private void ondxp(ActionEvent event) {
        if (taskDistance.isRunning() || taskGlobal1.isRunning() || taskMulti.isRunning() || file == null) {
            return;
        }
        loadDistance();
    }

    @FXML
    private void ontxs(ActionEvent event) {
        if (taskTime.isRunning() || taskGlobal1.isRunning() || taskMulti.isRunning() || file == null) {
            return;
        }
        loadTime();
    }

    @FXML
    private void ontxhr(ActionEvent event) {
        if (taskTime.isRunning() || taskGlobal1.isRunning() || taskMulti.isRunning() || file == null) {
            return;
        }
        loadTime();
    }

    @FXML
    private void ontxp(ActionEvent event) {
        if (taskTime.isRunning() || taskGlobal1.isRunning() || taskMulti.isRunning() || file == null) {
            return;
        }
        loadTime();
    }

    private void loadDistance() {

        ObservableList<Chunk> chunks = trackData.getChunks();

        taskDistance = new Task<help>() {
            @Override
            protected help call() throws Exception {

                startProgress();

                XYChart.Series speed = new XYChart.Series();
                XYChart.Series HR = new XYChart.Series();
                speed.setName("Speed");
                HR.setName("Heart Rate");

                XYChart.Series ped = new XYChart.Series();
                ped.setName("Pedaling Rate");

                double prog = 0;
                int co = 0;

                if (speedDist.isSelected()) {
                    co++;
                }
                if (hrDist.isSelected()) {
                    co++;
                }
                if (pedalingDist.isSelected()) {
                    co++;
                }

                double count = chunks.size() * co;
                double sum = 1 / count;

                if (speedDist.isSelected()) {
                    double dist = 0.0;
                    for (int i = 0; i < chunks.size(); i++) {
                        if (i != 0) {
                            dist += chunks.get(i).getDistance();
                        }
                        speed.getData().add(new XYChart.Data(dist, chunks.get(i).getSpeed()));
                        sumProgress(sum);
                    }
                }

                if (hrDist.isSelected()) {
                    double dist = 0.0;
                    for (int i = 0; i < trackData.getChunks().size(); i++) {
                        if (i != 0) {
                            dist += chunks.get(i).getDistance();
                        }
                        HR.getData().add(new XYChart.Data(dist, chunks.get(i).getAvgHeartRate()));
                        sumProgress(sum);
                    }
                }

                if (pedalingDist.isSelected()) {
                    double dist = 0.0;
                    for (int i = 0; i < trackData.getChunks().size(); i++) {
                        if (i != 0) {
                            dist += chunks.get(i).getDistance();
                        }
                        ped.getData().add(new XYChart.Data(dist, chunks.get(i).getAvgCadence()));
                        sumProgress(sum);
                    }
                }
                finishProgress();
                help ret = new help();

                ArrayList<XYChart.Series> aux = new ArrayList<>();
                aux.add(speed);
                aux.add(HR);
                aux.add(ped);

                ret.setData(aux);

                return ret;
            }
        };

        taskDistance.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    ArrayList<XYChart.Series> a = taskDistance.get().getData();
                    someDistChart.getData().setAll(a.get(0), a.get(1), a.get(2));
                } catch (Exception e) {

                }
            }
        });

        Thread th = new Thread(taskDistance);
        th.setDaemon(true);
        th.start();

    }

    private void loadTime() {

        ObservableList<Chunk> chunks = trackData.getChunks();

        taskTime = new Task<help>() {
            @Override
            protected help call() throws Exception {

                startProgress();

                XYChart.Series speed = new XYChart.Series();
                XYChart.Series HR = new XYChart.Series();
                speed.setName("Speed");
                HR.setName("Heart Rate");

                XYChart.Series ped = new XYChart.Series();
                ped.setName("Pedaling Rate");

                int co = 0;

                if (speedTime.isSelected()) {
                    co++;
                }
                if (hrTime.isSelected()) {
                    co++;
                }
                if (pedalingTime.isSelected()) {
                    co++;
                }

                double count = chunks.size() * co;
                double prog = 0;
                double sum = 1 / count;

                if (speedTime.isSelected()) {
                    double dist = 0.0;
                    for (int i = 0; i < chunks.size(); i++) {
                        if (i != 0) {
                            dist += chunks.get(i).getDuration().getSeconds();
                        }
                        speed.getData().add(new XYChart.Data(dist, chunks.get(i).getSpeed()));
                        sumProgress(sum);
                    }
                }

                if (hrTime.isSelected()) {
                    double dist = 0.0;
                    for (int i = 0; i < chunks.size(); i++) {
                        if (i != 0) {
                            dist += chunks.get(i).getDuration().getSeconds();
                        }
                        HR.getData().add(new XYChart.Data(dist, chunks.get(i).getAvgHeartRate()));
                        sumProgress(sum);
                    }
                }

                if (pedalingTime.isSelected()) {
                    double dist = 0.0;
                    for (int i = 0; i < chunks.size(); i++) {
                        if (i != 0) {
                            dist += chunks.get(i).getDuration().getSeconds();
                        }
                        ped.getData().add(new XYChart.Data(dist, chunks.get(i).getAvgCadence()));
                        sumProgress(sum);
                    }
                }

                finishProgress();
                help ret = new help();

                ArrayList<XYChart.Series> aux = new ArrayList<>();
                aux.add(speed);
                aux.add(HR);
                aux.add(ped);

                ret.setData(aux);

                return ret;
            }
        };

        taskTime.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    ArrayList<XYChart.Series> a = taskTime.get().getData();
                    someTimeChart.getData().setAll(a.get(0), a.get(1), a.get(2));
                } catch (Exception e) {

                }
            }
        });

        Thread th = new Thread(taskTime);
        th.setDaemon(true);
        th.start();

    }

    private void loadTraining() throws Exception {

        if (file == null) {
            return;
        }

        taskMulti = new Task<help>() {
            @Override
            protected help call() throws Exception {

                startProgress();

                XYChart.Series dist = new XYChart.Series<>();
                XYChart.Series time = new XYChart.Series<>();

                dist.setName("Distance (km)");
                time.setName("Time (min)");

                String dir = file.getAbsolutePath();

                help ret = new help();
                ArrayList<XYChart.Series> aux = new ArrayList<>();

                while (dir.charAt(dir.length() - 1) != 's') {
                    dir = dir.substring(0, dir.length() - 1);
                }
                count = Files.walk(Paths.get(dir)).count();
                sum = 1 / count;
                if (LastMonth.isSelected()) {

                    Files.walk(Paths.get(dir)).forEach(filePath -> {
                        if (Files.isRegularFile(filePath)) {
                            File file = filePath.toFile();
                            String sub = file.getName().substring(4, 6);
                            if (sub.charAt(0) == '0') {
                                sub = sub.substring(1);
                            }
                            sumProgress(sum);
                            if (sub.equals(Integer.toString(new Date().getMonth()))) {
                                try {
                                    JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class, TrackPointExtensionT.class);
                                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                                    JAXBElement<Object> root = (JAXBElement<Object>) unmarshaller.unmarshal(file);
                                    GpxType gpx = (GpxType) root.getValue();

                                    TrackData trackData = new TrackData(new Track(gpx.getTrk().get(0)));
                                    dist.getData().add(new XYChart.Data(file.getName().substring(6, 8) + "/" + file.getName().substring(4, 6), trackData.getTotalDistance() / 1000));
                                    time.getData().add(new XYChart.Data(file.getName().substring(6, 8) + "/" + file.getName().substring(4, 6), trackData.getTotalDuration().getSeconds() / 60));
                                } catch (Exception e) {
                                }
                            }
                        }
                    });

                    aux.add(dist);
                    aux.add(time);
                    ret.setData(aux);
                    return ret;

                } else {

                    Files.walk(Paths.get(dir)).forEach(filePath -> {
                        if (Files.isRegularFile(filePath)) {
                            File file = filePath.toFile();
                            String sub = file.getName().substring(4, 6);
                            if (sub.charAt(0) == '0') {
                                sub = sub.substring(1);
                            }
                            sumProgress(sum);
                            int month = Integer.parseInt(sub);
                            if (new Date().getMonth() - month <= 2) {
                                try {
                                    JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class, TrackPointExtensionT.class);
                                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                                    JAXBElement<Object> root = (JAXBElement<Object>) unmarshaller.unmarshal(file);
                                    GpxType gpx = (GpxType) root.getValue();

                                    TrackData trackData = new TrackData(new Track(gpx.getTrk().get(0)));
                                    dist.getData().add(new XYChart.Data(file.getName().substring(6, 8) + "/" + file.getName().substring(4, 6), trackData.getTotalDistance() / 1000));
                                    time.getData().add(new XYChart.Data(file.getName().substring(6, 8) + "/" + file.getName().substring(4, 6), trackData.getTotalDuration().getSeconds() / 60));
                                } catch (Exception e) {
                                    System.out.println("\n\n\n\nOli, yo soy otra, y soy mejor jajajaja");
                                }
                            }
                        }
                    });
                    aux.add(dist);
                    aux.add(time);
                    ret.setData(aux);
                    return ret;
                }

            }
        };

        taskMulti.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    finishProgress();
                    barChart.getData().setAll(taskMulti.get().getData().get(0), taskMulti.get().getData().get(1));
                } catch (Exception e) {
                    System.out.println("\n\n\n\n Oli, soy una excepción, quiereme");
                }
            }
        });

        Thread th = new Thread(taskMulti);
        th.setDaemon(true);
        th.start();

    }

    @FXML
    private void onLast(ActionEvent event) {
        if (!LastMonth.isSelected()) {
            LastMonth.setSelected(true);
        } else {
            ThreeLastMonths.setSelected(false);
        }
        if (taskMulti.isRunning()) {
            return;
        }
        try {
            loadTraining();
        } catch (Exception e) {

        }
    }

    @FXML
    private void onThreeLast(ActionEvent event) {
        if (!ThreeLastMonths.isSelected()) {
            ThreeLastMonths.setSelected(true);
        } else {
            LastMonth.setSelected(false);
        }
        if (taskMulti.isRunning()) {
            return;
        }
        try {
            loadTraining();
        } catch (Exception e) {

        }
    }

    @FXML
    private void onAge(KeyEvent event) {
        if (taskPie.isRunning()) {
            event.consume();
            return;
        }
        if (!"0123456789".contains(event.getCharacter())) {
            event.consume();
            return;
        }
        if (Integer.parseInt(textAge.getText() + event.getCharacter()) > 100) {
            event.consume();
        }

    }

    @FXML
    private void onGenerate(ActionEvent event) {
        if (taskPie.isRunning() || textAge.getText().isEmpty() || trackData == null) {
            return;
        }
        ObservableList<Chunk> chunks = trackData.getChunks();
        taskPie = new Task<help2>() {
            @Override
            protected help2 call() throws Exception {
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

                int[] count = new int[5];

                int maxHeart = 223 - Integer.parseInt(textAge.getText());

                for (int i = 0; i < chunks.size(); i++) {
                    double aux = (chunks.get(i).getAvgHeartRate() / maxHeart) * 100;
                    if (aux < 60) {
                        count[0] = count[0] + 1;
                    }
                    if (aux < 70 || aux >= 60) {
                        count[1] = count[1] + 1;
                    }
                    if (aux < 80 || aux >= 70) {
                        count[2] = count[2] + 2;
                    }
                    if (aux < 90 || aux >= 80) {
                        count[3] = count[3] + 1;
                    }
                    if (aux >= 90) {
                        count[4] = count[4] + 1;
                    }
                }

                data.add(new PieChart.Data("Z1 Recovery", count[0]));
                data.add(new PieChart.Data("Z2 Endurance", count[1]));
                data.add(new PieChart.Data("Z3 Tempo", count[2]));
                data.add(new PieChart.Data("Z4 Threshold", count[3]));
                data.add(new PieChart.Data("Z5 Anaerobic", count[4]));

                help2 a = new help2();
                a.setData(data);

                return a;
            }
        };

        taskPie.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    pieChart.setData(taskPie.get().getData());
                    pieChart.setTitle("Heart Rate");
                } catch (Exception e) {

                }
            }
        });

        Thread th = new Thread(taskPie);
        th.setDaemon(true);
        th.start();

    }

    @FXML
    private void onKeyPresed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loadPieChart();
        }
    }

    private void loadPieChart() {
        if (taskPie.isRunning() || textAge.getText().isEmpty() || trackData == null) {
            return;
        }
        ObservableList<Chunk> chunks = trackData.getChunks();
        taskPie = new Task<help2>() {
            @Override
            protected help2 call() throws Exception {
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

                int[] count = new int[5];

                int maxHeart = 223 - Integer.parseInt(textAge.getText());

                for (int i = 0; i < chunks.size(); i++) {
                    double aux = (chunks.get(i).getAvgHeartRate() / maxHeart) * 100;
                    if (aux < 60) {
                        count[0] = count[0] + 1;
                    }
                    if (aux < 70 || aux >= 60) {
                        count[1] = count[1] + 1;
                    }
                    if (aux < 80 || aux >= 70) {
                        count[2] = count[2] + 2;
                    }
                    if (aux < 90 || aux >= 80) {
                        count[3] = count[3] + 1;
                    }
                    if (aux >= 90) {
                        count[4] = count[4] + 1;
                    }
                }

                data.add(new PieChart.Data("Z1 Recovery", count[0]));
                data.add(new PieChart.Data("Z2 Endurance", count[1]));
                data.add(new PieChart.Data("Z3 Tempo", count[2]));
                data.add(new PieChart.Data("Z4 Threshold", count[3]));
                data.add(new PieChart.Data("Z5 Anaerobic", count[4]));

                help2 a = new help2();
                a.setData(data);

                return a;
            }
        };

        taskPie.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    pieChart.setData(taskPie.get().getData());
                    pieChart.setTitle("Heart Rate");
                } catch (Exception e) {

                }
            }
        });

        Thread th = new Thread(taskPie);
        th.setDaemon(true);
        th.start();

    }

}
