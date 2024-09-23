# Build
custom_build(
    # Name of the container image
    ref = 'edge-service',
    # Command to build the container image
    command = './gradlew bootBuildImage --imageName $EXPECTED_REF',
    # build.gradle 파일이나 src 디렉토리 내의 파일이 변경되면 새로운 빌드가 트리거됨
    deps = ['build.gradle', 'src']
)

# Deploy
k8s_yaml(kustomize('k8s'))

# Manage
k8s_resource('edge-service', port_forwards=['9000'])