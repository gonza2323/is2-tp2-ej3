import { Card, Center, Container, Image, Stack, Text, Title } from "@mantine/core";

export function Home() {
  return (
    <>
      <Container>
        <Stack>
          <Title>Home Page</Title>
          <Text>Qué tal?</Text>
          <Center>
            <Card>
              <Text>Una carta</Text>
            </Card>
          </Center>
        </Stack>
      </Container>
    </>
  );
}
