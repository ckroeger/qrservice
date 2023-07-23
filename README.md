# qrservice

A simple webservice to create qr-codes.

![](docs/qrcode.png)

The service starts at default on port ``8080``.
Maximum ``with/height`` is ``800`` pixels.

## 🏃 start as docker-container
```shell
docker run -p 8080:8080 -d ghcr.io/ckroeger/qrservice:0.4.0-prerelease0
```

## 🌐 usage
Example Urls:
* Generates a qr-code with "Hello" (default is 350x350)
  * http://localhost:8080/qr/?text=Hello
* Generates a qr-code with "Hello" (450x450)
  * http://localhost:8080/qr/?text=Hello&width=450&height=450
* Generates a qr-code with "Hello" uses a filename when save in browser (default is 350x350)
  * http://localhost:8080/qr/qrcode.png?text=Hello
* Generates a qr-code with "Hello" and trigger a filedownload in browser - content-disposition (default is 350x350)
  * http://localhost:8080/qr/qrcode.png?text=Hello&asFile=true
* Genreric Url, fileName is optional
* http://localhost:8080/qr/{fileName}?text=Hello&width=450&height=450
* Swagger API-Doc
  * API-Doc http://localhost:8080/swagger-ui/index.html

## ✅ ❌ open todos
Still not using github-issues to manage features and bugs 👼. 

List:
* ❌ add release-info in readme.md
* ✅ add favicon
* ✅ Add Image creation and github package release
* ✅ Pom-Version in Swagger-Doc -> AppConfig
* ✅ Add Spring Rest Doc
* ✅ Set Version to maven

## 📖 Knowledge-Sources
* REST
  * [Chapter 1: Introduction to REST APIs | I'd Rather Be Writing Blog and API doc course](https://idratherbewriting.com/learnapidoc/docapis_introtoapis.html)
* SemVer for GH-Action:
  * [git - Github versioning: is it possible to have two separate sequences in the tags? - Stack Overflow](https://stackoverflow.com/questions/73121689/github-versioning-is-it-possible-to-have-two-separate-sequences-in-the-tags)
  * [actions/upload-release-asset: An Action to upload a release asset via the GitHub Release API](https://github.com/actions/upload-release-asset)
* Maven
  * CI - Topic - Versioning
    * [Maven – Maven CI Friendly Versions](https://maven.apache.org/maven-ci-friendly.html)
    * [Simplified application versioning with maven](https://blog.pchudzik.com/201905/maven-revision/)
    * [Faster release with Maven CI Friendly Versions and a customised flatten plugin | by Avi Youkhananov | Outbrain Engineering | Medium](https://medium.com/outbrain-engineering/faster-release-with-maven-ci-friendly-versions-and-a-customised-flatten-plugin-fe53f0fcc0df)
  * Plugins
    * [License Maven Plugin – Introduction](https://www.mojohaus.org/license-maven-plugin/)
    * [Maven Flatten Plugin – Flatten Maven Plugin](https://www.mojohaus.org/flatten-maven-plugin/)
    * [Apache Maven Archiver – Manifest](https://maven.apache.org/shared/maven-archiver/examples/manifest.html)
* Spring
  * [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
  * Banner.txt
    * [java - Application version does not show up in Spring Boot banner.txt - Stack Overflow](https://stackoverflow.com/questions/34519759/application-version-does-not-show-up-in-spring-boot-banner-txt)
* Favicon
  * [Favicon Generator - Image to Favicon - favicon.io](https://favicon.io/favicon-converter/)
  * [Guide to the Favicon in Spring Boot | Baeldung](https://www.baeldung.com/spring-boot-favicon)
* Emoji
  * [Emoji Finder 😅 Search for Emoji](https://emojifinder.com/)

## 🧑‍💻️ Development
This Service is implemented with spring boot v3.1.x and needs at least Java 17 and Maven 3.

### Build docker-image locally
First package with maven-build:
```shell
mvn clean package
```

Build image:
```shell
docker build -t qrservice .
```

Run container:
```shell
docker run -p 8080:8080 qrservice
```

## 🛟 Development hints

### Bump semVer
```shell
git commit --allow-empty -m "(MINOR) Empty commit, bumps MINOR version"
```

```shell
git commit --allow-empty -m "(MAJOR) Empty commit, bumps MAJOR version"
```

