package com.example.zhcs.bean;

import java.util.List;

public class S_CSDT_LV {
    private Integer lineId;
    private String lineName;
    private PreStepBean preStep;
    private NextStepBean nextStep;
    private String currentName;
    private Integer reachTime;

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public PreStepBean getPreStep() {
        return preStep;
    }

    public void setPreStep(PreStepBean preStep) {
        this.preStep = preStep;
    }

    public NextStepBean getNextStep() {
        return nextStep;
    }

    public void setNextStep(NextStepBean nextStep) {
        this.nextStep = nextStep;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public Integer getReachTime() {
        return reachTime;
    }

    public void setReachTime(Integer reachTime) {
        this.reachTime = reachTime;
    }

    public static class PreStepBean {
        private String name;
        private List<LinesBean> lines;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LinesBean> getLines() {
            return lines;
        }

        public void setLines(List<LinesBean> lines) {
            this.lines = lines;
        }

        public static class LinesBean {
            private Integer lineId;
            private String lineName;

            public Integer getLineId() {
                return lineId;
            }

            public void setLineId(Integer lineId) {
                this.lineId = lineId;
            }

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }
        }
    }

    public static class NextStepBean {
        private String name;
        private List<LinesBean> lines;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LinesBean> getLines() {
            return lines;
        }

        public void setLines(List<LinesBean> lines) {
            this.lines = lines;
        }

        public static class LinesBean {
            private Integer lineId;
            private String lineName;

            public Integer getLineId() {
                return lineId;
            }

            public void setLineId(Integer lineId) {
                this.lineId = lineId;
            }

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }
        }
    }
}
