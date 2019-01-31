package com.qing.springboot.bean;

import java.util.List;

public class ResultData {

    private int code;
    private List<DataBeanXX> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBeanXX> getData() {
        return data;
    }

    public void setData(List<DataBeanXX> data) {
        this.data = data;
    }

    public static class DataBeanXX {

        private int id;
        private int pid;
        private String title;
        private String description;
        private Object childs;
        private String icon;
        private String dataType;
        private String style;
        private String dataSource;
        private String sql;
        private List<DataBeanX> data;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getChilds() {
            return childs;
        }

        public void setChilds(Object childs) {
            this.childs = childs;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getDataSource() {
            return dataSource;
        }

        public void setDataSource(String dataSource) {
            this.dataSource = dataSource;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public List<DataBeanX> getData() {
            return data;
        }

        public void setData(List<DataBeanX> data) {
            this.data = data;
        }

        public static class DataBeanX {

            private String dataSource;
            private String dataType;
            private String description;
            private String icon;
            private int id;
            private int pid;
            private String sql;
            private String style;
            private String title;
            private List<?> childs;
            private List<DataBean> data;

            public String getDataSource() {
                return dataSource;
            }

            public void setDataSource(String dataSource) {
                this.dataSource = dataSource;
            }

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getSql() {
                return sql;
            }

            public void setSql(String sql) {
                this.sql = sql;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<?> getChilds() {
                return childs;
            }

            public void setChilds(List<?> childs) {
                this.childs = childs;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {

                private int allTalkCount;
                private boolean alreadyClickGood;
                private String coverUri;
                private long created;
                private String description;
                private String duration;
                private boolean enableSpeed;
                private String exd;
                private int goodCount;
                private int grade;
                private int id;
                private int isAlreadyGrade;
                private boolean isFree;
                private int playCount;
                private int price;
                private String publishTime;
                private String title;
                private int type;
                private String videoUri;
                private List<String> screenShortUrls;

                public int getAllTalkCount() {
                    return allTalkCount;
                }

                public void setAllTalkCount(int allTalkCount) {
                    this.allTalkCount = allTalkCount;
                }

                public boolean isAlreadyClickGood() {
                    return alreadyClickGood;
                }

                public void setAlreadyClickGood(boolean alreadyClickGood) {
                    this.alreadyClickGood = alreadyClickGood;
                }

                public String getCoverUri() {
                    return coverUri;
                }

                public void setCoverUri(String coverUri) {
                    this.coverUri = coverUri;
                }

                public long getCreated() {
                    return created;
                }

                public void setCreated(long created) {
                    this.created = created;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public boolean isEnableSpeed() {
                    return enableSpeed;
                }

                public void setEnableSpeed(boolean enableSpeed) {
                    this.enableSpeed = enableSpeed;
                }

                public String getExd() {
                    return exd;
                }

                public void setExd(String exd) {
                    this.exd = exd;
                }

                public int getGoodCount() {
                    return goodCount;
                }

                public void setGoodCount(int goodCount) {
                    this.goodCount = goodCount;
                }

                public int getGrade() {
                    return grade;
                }

                public void setGrade(int grade) {
                    this.grade = grade;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIsAlreadyGrade() {
                    return isAlreadyGrade;
                }

                public void setIsAlreadyGrade(int isAlreadyGrade) {
                    this.isAlreadyGrade = isAlreadyGrade;
                }

                public boolean isIsFree() {
                    return isFree;
                }

                public void setIsFree(boolean isFree) {
                    this.isFree = isFree;
                }

                public int getPlayCount() {
                    return playCount;
                }

                public void setPlayCount(int playCount) {
                    this.playCount = playCount;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getPublishTime() {
                    return publishTime;
                }

                public void setPublishTime(String publishTime) {
                    this.publishTime = publishTime;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getVideoUri() {
                    return videoUri;
                }

                public void setVideoUri(String videoUri) {
                    this.videoUri = videoUri;
                }

                public List<String> getScreenShortUrls() {
                    return screenShortUrls;
                }

                public void setScreenShortUrls(List<String> screenShortUrls) {
                    this.screenShortUrls = screenShortUrls;
                }
            }
        }
    }
}
