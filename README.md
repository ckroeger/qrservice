# qrservice

A simple webservice to create qr-codes.

![](docs/qrcode.png)

The service starts at default on port ``8080``.
Maximum ``with/height`` is ``800`` pixels.

## usage
Example Urls:
* http://localhost:8080/qrcode?text=Hallo
* http://localhost:8080/qrcode?text=Hallo&width=450&height=450

## todos
* Add Spring Rest Doc
* Add Image creation and github package release
* ✅ Set Version to maven


## Knowledge-Sources
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

## Development hints

### Bump semVer
```shell
git commit --allow-empty -m "(MINOR) Empty commit, bumps MINOR version"
```

```shell
git commit --allow-empty -m "(MAJOR) Empty commit, bumps MAJOR version"
```

