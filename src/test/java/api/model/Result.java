package api.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gender",
        "name",
        "location",
        "email",
        "login",
        "dob",
        "registered",
        "phone",
        "cell",
        "id",
        "picture",
        "nat"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("email")
    private String email;
    @JsonProperty("login")
    private Login login;
    @JsonProperty("dob")
    private Dob dob;
    @JsonProperty("registered")
    private Registered registered;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("cell")
    private String cell;
    @JsonProperty("id")
    private Id id;
    @JsonProperty("picture")
    private Picture picture;
    @JsonProperty("nat")
    private String nat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("gender")
    public Gender getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("login")
    public Login getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(Login login) {
        this.login = login;
    }

    @JsonProperty("dob")
    public Dob getDob() {
        return dob;
    }

    @JsonProperty("dob")
    public void setDob(Dob dob) {
        this.dob = dob;
    }

    @JsonProperty("registered")
    public Registered getRegistered() {
        return registered;
    }

    @JsonProperty("registered")
    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("cell")
    public String getCell() {
        return cell;
    }

    @JsonProperty("cell")
    public void setCell(String cell) {
        this.cell = cell;
    }

    @JsonProperty("id")
    public Id getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id id) {
        this.id = id;
    }

    @JsonProperty("picture")
    public Picture getPicture() {
        return picture;
    }

    @JsonProperty("picture")
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @JsonProperty("nat")
    public String getNat() {
        return nat;
    }

    @JsonProperty("nat")
    public void setNat(String nat) {
        this.nat = nat;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("gender");
        sb.append('=');
        sb.append(((this.gender == null)?"<null>":this.gender));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null)?"<null>":this.location));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("login");
        sb.append('=');
        sb.append(((this.login == null)?"<null>":this.login));
        sb.append(',');
        sb.append("dob");
        sb.append('=');
        sb.append(((this.dob == null)?"<null>":this.dob));
        sb.append(',');
        sb.append("registered");
        sb.append('=');
        sb.append(((this.registered == null)?"<null>":this.registered));
        sb.append(',');
        sb.append("phone");
        sb.append('=');
        sb.append(((this.phone == null)?"<null>":this.phone));
        sb.append(',');
        sb.append("cell");
        sb.append('=');
        sb.append(((this.cell == null)?"<null>":this.cell));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("picture");
        sb.append('=');
        sb.append(((this.picture == null)?"<null>":this.picture));
        sb.append(',');
        sb.append("nat");
        sb.append('=');
        sb.append(((this.nat == null)?"<null>":this.nat));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
