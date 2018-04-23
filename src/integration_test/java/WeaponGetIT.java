import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class WeaponGetIT {


    @Test
    public void should_200_On_Existing_Weapon() throws IOException, URISyntaxException {


        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/weapons/" + 5).toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_OK);

    }

    @Test
    public void should_404_On_Non_Existing_Weapon() throws IOException, URISyntaxException {

        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/weapons/" + 1024).toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_NOT_FOUND);

    }

    @Test
    public void should_200_On_all_Weapons() throws IOException, URISyntaxException {

        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/weapons/").toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_OK);

    }

    @Test
    public void should_200_On_arsenal() throws IOException, URISyntaxException {

        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/arsenal/").toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_OK);

    }

    @Test
    public void should_200_On_myWeapons() throws IOException, URISyntaxException {

        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/myWeapons/").toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_OK);

    }

    @Test
    public void should_200_On_validate() throws IOException, URISyntaxException {

        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/validateWeapons/" + 1024).toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_OK);
    }



    @Test
    public void should_create_on_addweapon() throws IOException, URISyntaxException {


        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 +
                "/rep/addWeapon?name=&type=&group=&range=&mode=&damage=&penetration=&autonomy=&reload=&attributes=&weight=&owner=titus").toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_OK);

    }

    @Test
    public void weapon_should_have_been_created() throws IOException, URISyntaxException {

        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/weapons/" + 6).toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        HttpEntity entity = response.getEntity();

        InputStream inputStream = entity.getContent();

        BufferedReader streamReader = new BufferedReader(new InputStreamReader( inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) responseStrBuilder.append(inputStr);

        assertThat(responseStrBuilder.toString().equals("[\n" +
                "{\n" +
                "id: 7,\n" +
                "name: \"\",\n" +
                "type: \"\",\n" +
                "group: \"\",\n" +
                "range: \"\",\n" +
                "mode: \"\",\n" +
                "damage: \"\",\n" +
                "penetration: \"\",\n" +
                "autonomy: \"\",\n" +
                "reload: \"\",\n" +
                "attributes: \"\",\n" +
                "weight: \"\",\n" +
                "owner: \"titus\",\n" +
                "state: \"Waiting validation\"\n" +
                "}\n" +
                "]")).isEqualTo(true);
    }

    @Test
    public void should_404_on_non_existing_weapon_validation() throws IOException, URISyntaxException {


        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/validateWeapons/1024").toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_NOT_FOUND);

    }

    @Test
    public void should_validate_on_existing_weapon_validation() throws IOException, URISyntaxException {


        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/validateWeapons/6").toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_OK);

    }


    @Test
    public void weapon_should_have_been_validated() throws IOException, URISyntaxException {

        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/weapons/" + 6).toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        HttpEntity entity = response.getEntity();

        InputStream inputStream = entity.getContent();

        BufferedReader streamReader = new BufferedReader(new InputStreamReader( inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) responseStrBuilder.append(inputStr);

        assertThat(responseStrBuilder.toString().equals("[\n" +
                "{\n" +
                "id: 7,\n" +
                "name: \"\",\n" +
                "type: \"\",\n" +
                "group: \"\",\n" +
                "range: \"\",\n" +
                "mode: \"\",\n" +
                "damage: \"\",\n" +
                "penetration: \"\",\n" +
                "autonomy: \"\",\n" +
                "reload: \"\",\n" +
                "attributes: \"\",\n" +
                "weight: \"\",\n" +
                "owner: \"titus\",\n" +
                "state: \"Validated\"\n" +
                "}\n" +
                "]")).isEqualTo(true);
    }

    @Test
    public void should_404_on_non_existing_weapon_delete() throws IOException, URISyntaxException {


        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/weapons/1024/delete").toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_NOT_FOUND);

    }

    @Test
    public void should_delete_on_delete() throws IOException, URISyntaxException {


        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/weapons/6/delete").toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_OK);

    }

    @Test
    public void weapon_should_have_been_deleted() throws IOException, URISyntaxException {

        HttpUriRequest request = new HttpGet(new URL("http://localhost:" + 9135 + "/rep/weapons/" + 6).toURI());

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(SC_NOT_FOUND);

    }


}
