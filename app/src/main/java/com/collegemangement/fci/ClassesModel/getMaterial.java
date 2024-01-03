package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getMaterial {
    @SerializedName("material")
    @Expose
    private List<Material> material = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(List<Material> material) {
        this.material = material;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Material {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("Level")
        @Expose
        private String level;
        @SerializedName("FilePath")
        @Expose
        private String filePath;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("materialuploader")
        @Expose
        private Materialuploader materialuploader;
        @SerializedName("materialType")
        @Expose
        private String materialType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Materialuploader getMaterialuploader() {
            return materialuploader;
        }

        public void setMaterialuploader(Materialuploader materialuploader) {
            this.materialuploader = materialuploader;
        }

        public String getMaterialType() {
            return materialType;
        }

        public void setMaterialType(String materialType) {
            this.materialType = materialType;
        }


        public class Materialuploader {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("Name")
            @Expose
            private String name;
            @SerializedName("Email")
            @Expose
            private String email;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("ProfileImagePath")
            @Expose
            private String profileImagePath;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getProfileImagePath() {
                return profileImagePath;
            }

            public void setProfileImagePath(String profileImagePath) {
                this.profileImagePath = profileImagePath;
            }

        }
    }
}