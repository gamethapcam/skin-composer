/*******************************************************************************
 * MIT License
 * 
 * Copyright (c) 2017 Raymond Buckley
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.ray3k.skincomposer.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class CustomProperty implements Json.Serializable {
    private String name;
    private Object value;
    private CustomStyle parentStyle;
    private PropertyType type;
    public static enum PropertyType {
        NONE("None"), NUMBER("Number"), TEXT("Text"), DRAWABLE("Drawable"), FONT("Font"), COLOR("Color");

        String name;
        PropertyType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public CustomProperty() {
        
    }
    
    public CustomProperty(String name, PropertyType type) {
        this.name = name;
        this.type = type;
        
        switch (type) {
            case TEXT:
                value = "";
                break;
            case NUMBER:
                value = 0.0;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public CustomStyle getParentStyle() {
        return parentStyle;
    }

    public void setParentStyle(CustomStyle parentStyle) {
        this.parentStyle = parentStyle;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public CustomProperty copy() {
        CustomProperty returnValue = new CustomProperty(name, type);
        returnValue.parentStyle = parentStyle;
        returnValue.value = value;
        return returnValue;
    }

    @Override
    public void write(Json json) {
        json.writeValue("name", name);
        json.writeValue("type", type);
        json.writeValue("value", value);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        name = jsonData.getString("name");
        value = json.readValue("value", null, jsonData);
        type = json.readValue("type", PropertyType.class, jsonData);
    }
}